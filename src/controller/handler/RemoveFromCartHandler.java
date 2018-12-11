package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveFromCartHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = getService().getProduct(request.getParameter("productid"));

            String amount = request.getParameter("amount");
            if (amount == null) {
                getCart(request).remove(product);
            } else {
                getCart(request).removeAmount(product, Integer.parseInt(amount));
            }

            response.sendRedirect("Controller?action=mycart");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
