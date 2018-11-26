package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePreferencesHandler extends RequestHandler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String choice = request.getParameter("showquote");

        if (choice != null && (choice.equals("yes") || choice.equals("no"))) {
            response.addCookie(new Cookie("showquote", choice));
            response.sendRedirect("./");
        }
    }
}
