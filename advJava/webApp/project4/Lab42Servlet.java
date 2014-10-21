package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  This class demonstrates getting input fields from an HTML form
 *
 *@author    eknapp
 */
@WebServlet(
    name = "formsLab",
    urlPatterns = { "/forms-lab" }
)
public class Lab42Servlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter  out  = response.getWriter();

        String  param1  = request.getParameter("param1");
        out.print("<HTML><BODY>");
        out.print("param1: " + param1);

        String  param2  = request.getParameter("param2");

        out.print("param2: " + param2);
        out.print("/<BODY></HTML>");
    }

}

