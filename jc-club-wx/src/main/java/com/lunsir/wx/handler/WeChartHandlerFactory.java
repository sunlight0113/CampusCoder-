package com.lunsir.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类
 * @author lunSir
 * @create 2024-10-03 22:19
 */
@Component
public class WeChartHandlerFactory implements InitializingBean {

    @Resource
    private List<WeChartHandler> weChartHandlerList;

    private Map<WxMsgTypeEnum,WeChartHandler> handlerMap = new HashMap<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        for (WeChartHandler weChartHandler : weChartHandlerList) {
            handlerMap.put(weChartHandler.getSupportedWxMsgType(),weChartHandler);
        }
    }

    /**
     * 通过枚举类拿到相应处理策略的实现类
     */
    public WeChartHandler getWeChartHandlerByMsgType(WxMsgTypeEnum wxMsgTypeEnum){
        if (wxMsgTypeEnum == null) return null;
        return handlerMap.get(wxMsgTypeEnum);
    }
}
