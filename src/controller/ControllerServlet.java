package controller;

import domain.service.ShopService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/Controller"})
public class ControllerServlet extends HttpServlet {

    private HandlerFactory handlerFactory;

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        Properties properties = new Properties();
        properties.setProperty("url", context.getInitParameter("url"));
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("currentSchema", context.getInitParameter("currentSchema"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        ShopService service = new ShopService(properties);

        InputStream input = context.getResourceAsStream("/WEB-INF/handler.xml");
        Properties handlerProperties = new Properties();
        try {
            handlerProperties.loadFromXML(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handlerFactory = new HandlerFactory(handlerProperties, service);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, true);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, boolean isPostRequest)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "home";

        RequestHandler handler = handlerFactory.getHandler(action);

        if (handler == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            String destination = handler.handleRequest(request, response, isPostRequest);
            if (response.getStatus() != HttpServletResponse.SC_MOVED_TEMPORARILY) {
                if (destination != null) {
                    request.getRequestDispatcher(destination).forward(request, response);
                }
            }
        }
    }
}
