package controller.handler;

import controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeHandler extends RequestHandler {

    private List<String> quotes = new ArrayList<>();
    private Random random = new Random();

    public HomeHandler() {
        super();

        quotes.add("Too lazy to find quotes #1");
        quotes.add("Too lazy to find quotes #2");
        quotes.add("Too lazy to find quotes #3");
        quotes.add("Too lazy to find quotes #4");
        quotes.add("Too lazy to find quotes #5");
        quotes.add("Too lazy to find quotes #6");
        quotes.add("Too lazy to find quotes #7");
        quotes.add("Too lazy to find quotes #8");
        quotes.add("Too lazy to find quotes #9");
        quotes.add("Too lazy to find quotes #10");
        quotes.add("Too lazy to find quotes #11");
        quotes.add("Too lazy to find quotes #12");
        quotes.add("Too lazy to find quotes #13");
        quotes.add("Too lazy to find quotes #14");
        quotes.add("Too lazy to find quotes #15");
        quotes.add("Too lazy to find quotes #16");

    }

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("showquote")) {

                String choice = cookie.getValue();
                request.setAttribute("showquote", choice);
                if (choice.equals("yes"))
                    request.setAttribute("quote", quotes.get(random.nextInt(quotes.size())));

                break;
            }
        }

        setDestination("index.jsp");
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}
