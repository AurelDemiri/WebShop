package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        setDestination("signup.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Person person = new Person(userId, email, password, firstName, lastName);
            person.setPasswordHashed(password);
            getService().addPerson(person);
            response.sendRedirect("Controller?action=personoverview");
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptUserId", userId);
            request.setAttribute("keptFirstName", firstName);
            request.setAttribute("keptLastName", lastName);
            request.setAttribute("keptEmail", email);
        }

        setDestination("signup.jsp");
    }
}
