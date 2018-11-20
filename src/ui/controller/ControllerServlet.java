package ui.controller;

import domain.db.*;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Product;
import domain.service.ShopService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/Controller"})
public class ControllerServlet extends HttpServlet {

    private ShopService service;

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

        service = new ShopService(properties);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, true);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, boolean isPostRequest)
            throws ServletException, IOException {
        String destination = "index.jsp";
        String action = request.getParameter("action");

        if (action != null) {
            if (!isPostRequest) {
                switch (action) {
                    case "personoverview":
                        destination = getPersonOverview(request, response);
                        break;
                    case "signUp":
                        destination = getSignUp(request, response);
                        break;
                    case "productoverview":
                        destination = getProductsOverview(request, response);
                        break;
                    case "addproduct":
                        destination = getAddProduct(request, response);
                        break;
                    case "updateproduct":
                        destination = getUpdateProduct(request, response);
                        break;
                    case "deleteproduct":
                        destination = getDeleteProduct(request, response);
                        break;
                    case "deleteperson":
                        destination = getDeletePerson(request, response);
                        break;
                    case "checkpassword":
                        destination = getCheckPassword(request, response);
                        break;
                }
            } else {
                switch (action) {
                    case "signUp":
                        destination = postSignUp(request, response);
                        break;
                    case "addproduct":
                        destination = postAddProduct(request, response);
                        break;
                    case "updateproduct":
                        destination = postUpdateProduct(request, response);
                        break;
                    case "deleteproduct":
                        destination = postDeleteProduct(request, response);
                        break;
                    case "deleteperson":
                        destination = postDeletePerson(request, response);
                        break;
                    case "checkpassword":
                        destination = postCheckPassword(request, response);
                        break;
                }
            }
        }

        // Used to redirect user to homepage when he tries to access an invalid url
        if (destination.equals("index.jsp") && request.getQueryString() != null)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            if (response.getStatus() != HttpServletResponse.SC_MOVED_TEMPORARILY)
                request.getRequestDispatcher(destination).forward(request, response);
        }
    }

    private String getPersonOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("people", service.getPersons());
        return "personoverview.jsp";
    }

    private String getProductsOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", service.getProducts());
        return "productoverview.jsp";
    }

    private String getAddProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return "addproduct.jsp";
    }

    private String getUpdateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Product product = service.getProduct(request.getParameter("productid"));
            request.setAttribute("keptName", product.getName());
            request.setAttribute("keptDescription", product.getDescription());
            request.setAttribute("keptPrice", product.getPrice());
        } catch (DomainException e) {
            response.sendRedirect("Controller?action=productoverview");
        }
        return "updateproduct.jsp";
    }

    private String getDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            service.getProduct(request.getParameter("productid"));
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "deleteproduct.jsp";
    }

    private String getDeletePerson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            service.getPerson(request.getParameter("userid"));
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "deleteperson.jsp";
    }

    private String getSignUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return "signup.jsp";
    }

    private String getCheckPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            service.getPerson(request.getParameter("userid"));
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "checkpassword.jsp";
    }

    private String postSignUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Person person = new Person(userId, email, password, firstName, lastName);
            person.setPasswordHashed(password);
            service.addPerson(person);
            response.sendRedirect("Controller?action=personoverview");
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptUserId", userId);
            request.setAttribute("keptFirstName", firstName);
            request.setAttribute("keptLastName", lastName);
            request.setAttribute("keptEmail", email);
        }

        return getSignUp(request, response);
    }

    private String postAddProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            service.addProduct(new Product(name, description, price));
            response.sendRedirect("Controller?action=productoverview");
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptName", name);
            request.setAttribute("keptDescription", description);
            request.setAttribute("keptPrice", price);
        }

        return getAddProduct(request, response);
    }

    private String postUpdateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productid");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            Product product = service.getProduct(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            response.sendRedirect("Controller?action=productoverview");
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());

            // Set old values
            request.setAttribute("keptName", name);
            request.setAttribute("keptDescription", description);
            request.setAttribute("keptPrice", price);
        }

        return "updateproduct.jsp";
    }

    private String postDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ok = request.getParameter("ok");

        if (ok != null && ok.equals("Ok")) {
            String productId = request.getParameter("productid");
            try {
                Product product = service.getProduct(productId);
                service.deleteProduct(product.getProductId());
                response.sendRedirect("Controller?action=productoverview");
            }
            // NOTE: It might be dangerous to display a DBException to a user
            catch (DomainException | IllegalArgumentException | DbException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else {
            response.sendRedirect("Controller?action=productoverview");
        }

        return "deleteproduct.jsp";
    }

    private String postDeletePerson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ok = request.getParameter("ok");

        if (ok != null && ok.equals("Ok")) {
            String userId = request.getParameter("userid");

            try {
                Person person = service.getPerson(userId);
                service.deletePerson(person.getUserId());
                response.sendRedirect("Controller?action=personoverview");
            }
            // NOTE: It might be dangerous to display a DBException to a user
            catch (DomainException | IllegalArgumentException | DbException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else {
            response.sendRedirect("Controller?action=personoverview");
        }

        return "deleteperson.jsp";
    }

    private String postCheckPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");

        try {
            Person person = service.getPerson(userId);
            if (person.isPasswordCorrect(password)) {
                request.setAttribute("message", "The password is correct.");
            } else {
                request.setAttribute("message", "The password is NOT correct.");
            }
        }
        // NOTE: It might be dangerous to display a DBException to a user
        catch (DomainException | IllegalArgumentException | DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "checkpassword.jsp";
    }
}
