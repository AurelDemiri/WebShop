package controller.handler;

import controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyCartHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Required otherwise cart might be null
        getCart(request);
        setDestination("mycart.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("./");
    }
}
