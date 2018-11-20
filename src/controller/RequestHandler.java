package controller;

import domain.service.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    private ShopService service;
    private String destination;

    public String handleRequest(HttpServletRequest request, HttpServletResponse response, boolean isPostRequest) throws ServletException, IOException {
        if (!isPostRequest)
            handleGet(request, response);
        else
            handlePost(request, response);

        return destination;
    }

    public abstract void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public abstract void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void setDestination(String destination) {
        this.destination = destination;
    }

    public ShopService getService() {
        return service;
    }

    public void setService(ShopService service) {
        this.service = service;
    }
}
