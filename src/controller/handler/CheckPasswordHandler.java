package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswordHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getService().getUser(request.getParameter("userid"));
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        setDestination("checkpassword.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");

        try {
            User user = getService().getUser(userId);
            if (user.isPasswordCorrect(password)) {
                request.setAttribute("message", "The password is correct.");
            } else {
                request.setAttribute("message", "The password is NOT correct.");
            }
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        setDestination("checkpassword.jsp");
    }
}
