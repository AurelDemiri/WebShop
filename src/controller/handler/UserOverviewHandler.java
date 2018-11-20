package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserOverviewHandler extends RequestHandler {

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("people", getService().getPersons());
        setDestination("personoverview.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) {

    }
}
