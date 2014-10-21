package project4;

import java.io.*;
import java112.project4.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  This class demonstrates Forwarding an HTTP Request
 *
 *@author    eknapp
 */
@WebServlet(
    name = "employeeSearch",
    urlPatterns = { "/employee-search" }
)

public class EmployeeSearch extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               the HttpRequest
     *@param  response              the HttpResponse
     *@exception  ServletException  if there is a general
     *                              servlet exception
     *@exception  IOException       if there is a general
     *                              I/O exception
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException {

        //Create the url
        String url = "/jsp/employeeSearch.jsp";

        //Forward to jsp page
        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
