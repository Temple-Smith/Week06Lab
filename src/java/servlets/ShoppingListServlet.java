package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Annika Vestre
 */
public class ShoppingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username");

        if (action != null && action.equals("logout")) {
            session.invalidate();
            request.setAttribute("message", "Successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }

        if (username != null && !username.equals("")) {
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        String user = request.getParameter("username");
        String item = request.getParameter("item");
        Boolean shopping = true;
        
        if (action != null) {
            switch (action) {
                case "register":
                    if (user != null && !user.isEmpty()) {
                        items = new ArrayList<>();
                        
                        session.setAttribute("username", user);
                        session.setAttribute("items", items);
                    } else {
                        shopping = false;
                        request.setAttribute("error", "true");
                    }
                    break;
                case "add":
                    if (item != null && !item.isEmpty()) {
                        items.add(item);
                    } else {
                        request.setAttribute("error", "true");
                    }
                    break;
                case "delete":
                    items.remove(item);
            }
        }
        
        if (shopping) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
    }
}
