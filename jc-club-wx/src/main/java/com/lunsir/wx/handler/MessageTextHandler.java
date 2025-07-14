package com.lunsir.wx.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lunsir.wx.entity.AiMessage;
import com.lunsir.wx.entity.AiResponse;
import com.lunsir.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 处理用户发送消息的实现类
 *
 * @author lunSir
 * @create 2024-10-03 22:18
 */
@Component
@Slf4j
public class MessageTextHandler implements WeChartHandler {

    @Resource
    private RedisUtil redisUtil;


    private final String LOGIN_KEY = "login";

    private final String USER_CHAT_GML_KEY = "ai";

    private final String AI_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";

    /**
     * 处理用户发送消息
     */
    @Override
    public WxMsgTypeEnum getSupportedWxMsgType() {
        return WxMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMessage(Map<String, String> messageMap) {
        log.info(getSupportedWxMsgType().getDesc());
        String toUserName = messageMap.get("ToUserName");
        String fromUserName = messageMap.get("FromUserName");
        String content = messageMap.get("Content");
        if (content.equals("验证码")) {
            // 生成验证码key
            String codeKey = "code:" + fromUserName;
            // 先从redis拿验证码，防止用户多发，重发
            String code = redisUtil.get(codeKey);
            if (StringUtils.isBlank(code)) {
                // 生成6位的验证码
                code = RandomStringUtils.randomNumeric(6);
                // 存放到redis中，防止用户多发，重发
                redisUtil.setNx(codeKey,code,10L, TimeUnit.MINUTES);
                // 因为我们在登录的业务中，是拿不到openid的，所以我们要根据验证码拿到openid
                String loginKey = redisUtil.buildKey(LOGIN_KEY, code);
                redisUtil.setNx(loginKey,fromUserName,10L, TimeUnit.MINUTES);
            }
            content = "【lun先生】尊敬的用户，您好！您正在通过微信公众号进行用户注册行为，" +
                    "验证码为：" + code + " " +
                    "，有效期为10分钟。" +
                    "请及时输入验证码完成操作，并注意保密，" +
                    "不要将验证码告知他人。感谢您对我们的信任与支持！";
        }
        else {
//            // 1. 调用chatGML大模型
//            // 1.1 组装数据
//            AiMessage aiMessage = new AiMessage();
//            aiMessage.setRole("user");
//            aiMessage.setContent(content);
//            AiResponse aiResponse = new AiResponse();
//            LinkedList<AiMessage> aiMessageList = new LinkedList<>();
//            aiMessageList.add(aiMessage);
//            aiResponse.setModel("glm-4-plus");
//            // 1.2 从redis拿到记忆信息
//            String aiCodeKey = USER_CHAT_GML_KEY + ":" + fromUserName;
//            String respond = redisUtil.get(aiCodeKey);
//            if (StringUtils.isBlank(respond)){
//                // 说明redis中没有，证明用户第一次调用ai
//                // 就把数据放到redis中（记忆会话10分钟）
//                redisUtil.set(aiCodeKey,new Gson().toJson(aiMessageList),10L,TimeUnit.MINUTES);
//            }
//            // redis有数据，就做记忆会话
//            List<AiMessage> messageList =  new Gson().fromJson(respond, new TypeToken<List<AiMessage>>() {}.getType());
//            // 将原来的数据插入到前面
//            aiMessageList.addAll(0,messageList);
//            log.info("MessageTextHandler.redis.ai.{}.messageList:{}",fromUserName,new Gson().toJson(messageList));
//            aiResponse.setMessages(aiMessageList);
//            log.info("MessageTextHandler.redis.ai.{}请求ai的提交的数据.aiResponse:{}",fromUserName,new Gson().toJson(aiResponse));
//            redisUtil.set(aiCodeKey,new Gson().toJson(aiResponse.getMessages()),10L,TimeUnit.MINUTES);
//            // 发送请求
//            HttpHeaders header = new HttpHeaders();
//            header.set("Authorization", "17934d18c02750a573ba092e9f049415.lUGZkicCCv9Gfzcg");
//            header.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> resEntity = new HttpEntity<>(new Gson().toJson(aiResponse), header);
//            RestTemplate restTemplate = new RestTemplate();
//            String res = restTemplate.postForObject(AI_URL, resEntity, String.class);
//            // 截取信息
//            String resContent = res.substring(res.indexOf("content\":\"") + 10, res.indexOf("\",\"role"));
//            log.info("调用ai接口接受到的数据：{}",resContent);
//            // 生成记忆模式
//            AiMessage message = new AiMessage();
//            message.setContent(resContent);
//            message.setRole("assistant");
//            // 逻辑上说这个必须能取到，看上面的代码就行了
//            String chatInRedis = redisUtil.get(aiCodeKey);
//            List<AiMessage> messageListInRedis =  new Gson().fromJson(chatInRedis, new TypeToken<List<AiMessage>>() {}.getType());
//            messageListInRedis.add(message);
//            redisUtil.set(aiCodeKey,new Gson().toJson(messageListInRedis),10L,TimeUnit.MINUTES);
//            // 将数据发送
//            content = resContent;
            content = "unknown";
        }
        // 发送信息
        String msgTemplate = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + content + "]]></Content>\n" +
                "</xml>";
        return msgTemplate;
    }
}
