package murach.Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import murach.Business.User;
import murach.Data.UserDB;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng GET requests tới POST handler
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = "/index.jsp";

        // Get the current action
        String action = req.getParameter("action");
        if (action == null) {
            action = "join";  // Default action
        }

        // Perform action and set the URL to the appropriate page
        if (action.equals("join")) {
            url = "/index.jsp";  // The "join" page
        } else if (action.equals("add")) {
            // Get parameters from the request
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");

            // Create a new User object without ID
            User user = new User(email, firstName, lastName);

            // Validate the parameters
            String message = "";
            if (UserDB.emailExists(user.getEmail())) {
                message = "This email address already exists.<br>" +
                        "Please enter another email address.";
                url = "/index.jsp";
            } else {
                message = "";
                url = "/thanks.jsp";
                UserDB.insert(user);  // Add user to database
            }

            // Set attributes to pass to the JSP
            req.setAttribute("user", user);
            req.setAttribute("message", message);
        }

        // Forward the request and response to the appropriate page
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
