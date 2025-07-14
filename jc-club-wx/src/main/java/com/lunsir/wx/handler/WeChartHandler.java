package com.lunsir.wx.handler;

import java.util.Map;

/**
 * @author lunSir
 * @create 2024-10-03 22:12
 */
public interface WeChartHandler{

    // 得到支持处理的某一个事件类型的枚举
    WxMsgTypeEnum getSupportedWxMsgType();

    // 处理消息具体的细节
    String dealMessage(Map<String, String> messageMap);


}
