package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Product product = getService().getProduct(request.getParameter("productid"));
            request.setAttribute("keptName", product.getName());
            request.setAttribute("keptDescription", product.getDescription());
            request.setAttribute("keptPrice", product.getPrice());
        } catch (DomainException e) {
            response.sendRedirect("Controller?action=productoverview");
        }
        setDestination("updateproduct.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("productid");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            Product product = getService().getProduct(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            response.sendRedirect("Controller?action=productoverview");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptName", name);
            request.setAttribute("keptDescription", description);
            request.setAttribute("keptPrice", price);
        }

        setDestination("updateproduct.jsp");
    }
}
