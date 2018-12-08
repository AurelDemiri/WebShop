package controller.handler;

import controller.RequestHandler;
import domain.db.DbException;
import domain.model.DomainException;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getService().getUser(request.getParameter("userid"));
        } catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        setDestination("deleteuser.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ok = request.getParameter("ok");

        if (ok != null && ok.equals("Ok")) {
            String userId = request.getParameter("userid");

            try {
                User user = getService().getUser(userId);
                getService().deleteUser(String.valueOf(user.getUserId()));
                response.sendRedirect("Controller?action=useroverview");
            } catch (DomainException | IllegalArgumentException | DbException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else {
            response.sendRedirect("Controller?action=useroverview");
        }

        setDestination("deleteuser.jsp");
    }
}
