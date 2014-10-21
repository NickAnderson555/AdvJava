package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(
    name = "searchResults",
    urlPatterns = { "/search-results" }
)
public class SearchResultsServlet extends HttpServlet {

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

        HttpSession session = request.getSession();

        EmployeeDirectory employeeDirectory = (EmployeeDirectory)
                getServletContext().getAttribute("employeeDirectory");

        Search search = null;

        String searchTerm;
        String searchType;

        String searchParameter = request.getParameter("choice");

        if ("id".equals(searchParameter)) {
            searchType = "id";
        } else {
            searchType = "lastName";
        }

        searchTerm = request.getParameter("selection");

        search = employeeDirectory.searchForEmployee(searchType, searchTerm);
        session.setAttribute("search", search);
        //sessions.setAttribute("project4properties", properties);

        String url = "/jsp/searchResults.jsp";

        RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
    }

}
