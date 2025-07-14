package com.lunsir.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 处理用户关注事件的实现类
 * @author lunSir
 * @create 2024-10-03 22:15
 */
@Component
@Slf4j
public class SubscribeHandler implements WeChartHandler{


    /**
     * 处理用户关注事件
     */
    @Override
    public WxMsgTypeEnum getSupportedWxMsgType() {
        return WxMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMessage(Map<String, String> messageMap) {
        log.info(getSupportedWxMsgType().getDesc());
        String toUserName = messageMap.get("ToUserName");
        String fromUserName = messageMap.get("FromUserName");
        String content = "欢迎关注lun先生的公众号，我们一起共同进步！";

        String msgTemplate = "<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+content+"]]></Content>\n" +
                "</xml>";
        return msgTemplate;
    }
}
