package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearCartHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getCart(request).clear();
        response.sendRedirect("Controller?action=mycart");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
