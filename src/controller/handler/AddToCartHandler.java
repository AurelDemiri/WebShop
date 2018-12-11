package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCartHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Product product = getService().getProduct(request.getParameter("productid"));
            getCart(request).add(product);
            response.sendRedirect("Controller?action=mycart");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
