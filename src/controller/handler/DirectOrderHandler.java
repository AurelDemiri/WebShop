package controller.handler;

import controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DirectOrderHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request)) {
            response.sendRedirect("Controller?action=login");
        } else {
            if (getCart(request).uniqueSize() == 0)
                request.setAttribute("error", "No items in your cart... Nice try :)");

            getCart(request).clear();
            setDestination("directorder.jsp");
        }
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
