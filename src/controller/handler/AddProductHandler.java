package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        setDestination("addproduct.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            getService().addProduct(new Product(name, description, price));
            response.sendRedirect("Controller?action=productoverview");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptName", name);
            request.setAttribute("keptDescription", description);
            request.setAttribute("keptPrice", price);
        }

        setDestination("addproduct.jsp");
    }
}
