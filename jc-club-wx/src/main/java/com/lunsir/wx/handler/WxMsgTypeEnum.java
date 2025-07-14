package com.lunsir.wx.handler;

import lombok.Getter;

/**
 * @author lunSir
 * @create 2024-10-03 21:59
 */
@Getter
public enum WxMsgTypeEnum {
    SUBSCRIBE("event.subscribe", "用户关注公众号事件"),
    TEXT_MSG("text", "用户发送消息行为");

    private String messageType;
    private String desc;

    WxMsgTypeEnum(String messageType, String desc) {
        this.messageType = messageType;
        this.desc = desc;
    }

    public static WxMsgTypeEnum getInstanceByMessageType(String messageType) {
        for (WxMsgTypeEnum wxMsgTypeEnum : WxMsgTypeEnum.values()) {
            if (wxMsgTypeEnum.getMessageType().equals(messageType)) {
                return wxMsgTypeEnum;
            }
        }
        return null;
    }

}
