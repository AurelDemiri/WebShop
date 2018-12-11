package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isUserLoggedIn(request))
            response.sendRedirect("./");

        setDestination("login.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isUserLoggedIn(request)) {
            response.sendRedirect("./");
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            request.getSession().setAttribute("loggedinUser", getService().getUserIfAuthenticated(email, password));
            response.sendRedirect("./");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptEmail", email);
        }

        setDestination("login.jsp");
    }
}
