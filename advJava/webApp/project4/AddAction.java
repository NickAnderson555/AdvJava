package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(
    name = "processEmployee",
    urlPatterns = { "/process-employee" }
)
public class AddAction extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        EmployeeDirectory employeeDirectory =
                (EmployeeDirectory) getServletContext().getAttribute("employeeDirectory");

        String id = request.getParameter("id");
        int empId = Integer.parseInt(id);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String ssn = request.getParameter("ssn");
        String department = request.getParameter("department");
        String room = request.getParameter("room");
        String phone = request.getParameter("phone");

        String result = employeeDirectory.addEmployee(empId, firstName, lastName, ssn,
                                    department, room, phone);

        session.setAttribute("result", result);

        String url = "/AdvJava/employee-add";

        response.sendRedirect(url);
    }

}
