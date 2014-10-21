package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  This is part of module 4 and demonstrates
 *  getting and setting attributes in the session.
 *
 *@author    eknapp
 */
@WebServlet(
    name = "gettingSettingAttribute",
    urlPatterns = { "/sessionSetting" }
)
public class Project4SessionLab extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer sessionCounter = (Integer) session.getAttribute("project4SessionCounter");

        if (sessionCounter == null) {

            sessionCounter = new Integer(1);

            session.setAttribute("project4SessionCounter", sessionCounter);

        } else {

           sessionCounter++;

           session.setAttribute("project4SessionCounter", sessionCounter);

        }

        //Create the url
        String url = "/project4Session.jsp";

        //Forward to jsp page
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}

