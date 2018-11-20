package controller;

import domain.service.ShopService;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HandlerFactory {
    private Map<String, RequestHandler> handlers = new HashMap<>();

    public HandlerFactory(Properties handlerNames, ShopService service) {
        for (Object key : handlerNames.keySet()) {
            RequestHandler handler;
            String handlerName = handlerNames.getProperty((String) key);
            Class<?> handlerClass;
            try {
                handlerClass = Class.forName(handlerName);
                Object handlerObject = handlerClass.newInstance();
                handler = (RequestHandler) handlerObject;
                handler.setService(service);
                handlers.put((String) key, handler);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public RequestHandler getHandler(String key) {
        return handlers.get(key);
    }
}