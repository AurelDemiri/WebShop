package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getService().getProduct(request.getParameter("productid"));
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        setDestination("deleteproduct.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ok = request.getParameter("ok");

        if (ok != null && ok.equals("Ok")) {
            String productId = request.getParameter("productid");
            try {
                Product product = getService().getProduct(productId);
                getService().deleteProduct(product.getProductId());
                response.sendRedirect("Controller?action=productoverview");
            } catch (DomainException | IllegalArgumentException | DbException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else {
            response.sendRedirect("Controller?action=productoverview");
        }

        setDestination("deleteproduct.jsp");
    }
}
