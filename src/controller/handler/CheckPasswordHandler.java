package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswordHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getService().getPerson(request.getParameter("userid"));
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
            Person person = getService().getPerson(userId);
            if (person.isPasswordCorrect(password)) {
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
