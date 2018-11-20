package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        setDestination("index.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) {

    }
}
