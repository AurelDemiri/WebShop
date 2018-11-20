package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductOverviewHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("products", getService().getProducts());
        setDestination("productoverview.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) {

    }
}
