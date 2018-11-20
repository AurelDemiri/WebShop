package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePersonHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getService().getPerson(request.getParameter("userid"));
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        setDestination("deleteperson.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ok = request.getParameter("ok");

        if (ok != null && ok.equals("Ok")) {
            String userId = request.getParameter("userid");

            try {
                Person person = getService().getPerson(userId);
                getService().deletePerson(person.getUserId());
                response.sendRedirect("Controller?action=personoverview");
            } catch (DomainException | IllegalArgumentException | DbException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else {
            response.sendRedirect("Controller?action=personoverview");
        }

        setDestination("deleteperson.jsp");
    }
}
