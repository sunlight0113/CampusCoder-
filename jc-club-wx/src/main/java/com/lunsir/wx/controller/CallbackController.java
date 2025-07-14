package com.lunsir.wx.controller;

import com.lunsir.wx.handler.WeChartHandler;
import com.lunsir.wx.handler.WeChartHandlerFactory;
import com.lunsir.wx.handler.WxMsgTypeEnum;
import com.lunsir.wx.utils.MessageUtil;
import com.lunsir.wx.utils.SHA1;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Period;
import java.util.Map;
import java.util.Objects;

/**
 * @author lunSir
 * @create 2024-10-03 20:32
 */
@RestController
@Slf4j
public class CallbackController {
    private final String token = "perrynamelunsir";

    @Resource
    private WeChartHandlerFactory weChartHandlerFactory;

    @GetMapping("/callback")
    public String callback(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr){

        log.info("微信验证签名参数：signature：{},timestamp：{},nonce：{},echostr：{}",signature,timestamp,nonce,echostr);
        // 检验签名
        String sha1 = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(sha1)){
            // 校验成功
            return echostr;
        }
        return "unknown";
    }

    @PostMapping(value = "/callback",produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
    public String callback(@RequestBody String requestBody,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam(value = "msg_signature",required = false) String msgSignature){
        // 打印日志
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String msgType = messageMap.get("MsgType");
        String event = messageMap.get("Event") == null ? "" : messageMap.get("Event");
        if(StringUtils.isNotBlank(event)){
            msgType = msgType + "." + event;
        }
        log.info("msgType：{}",msgType);
        // 通过msgType找到枚举类
        WxMsgTypeEnum wxMsgTypeEnum = WxMsgTypeEnum.getInstanceByMessageType(msgType);
        WeChartHandler weChartHandler = weChartHandlerFactory.getWeChartHandlerByMsgType(wxMsgTypeEnum);
        if (Objects.isNull(weChartHandler)){
            return "unknown";
        }
        return weChartHandler.dealMessage(messageMap);
    }

    // 用户向公众号发送消息模版
//    <xml><ToUserName><![CDATA[gh_a6b27b1508ef]]></ToUserName>
//    <FromUserName><![CDATA[oUZNI6yRbz7hiXXGKJNls_yYUPA0]]></FromUserName>
//    <CreateTime>1727962555</CreateTime>
//    <MsgType><![CDATA[text]]></MsgType>
//    <Content><![CDATA[你好]]></Content>
//    <MsgId>24738476716463467</MsgId>
//    </xml>

    // 用户关注公众号模版
//    <xml><ToUserName><![CDATA[gh_a6b27b1508ef]]></ToUserName>
//    <FromUserName><![CDATA[oUZNI60QwgjC-4RviyrMsKRKGbJ4]]></FromUserName>
//    <CreateTime>1727963727</CreateTime>
//    <MsgType><![CDATA[event]]></MsgType>
//    <Event><![CDATA[subscribe]]></Event>
//    <EventKey><![CDATA[]]></EventKey>
//    </xml>

}
