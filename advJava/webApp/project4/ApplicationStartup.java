package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(
    name = "applicationStartup",
    urlPatterns = { "/project4-startup" },
    loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {

    public void init() throws ServletException {

        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream("/project4.properties"));

            } catch(IOException ioe) {
                System.out.println("Cant load the properties file");
                ioe.printStackTrace();

            } catch(Exception e) {
                System.out.println("Problem: " + e);
                e.printStackTrace();

        } finally {

            ServletContext context = getServletContext();
            context.setAttribute("project4properties", properties);
            EmployeeDirectory employees = new EmployeeDirectory(properties);
            context.setAttribute("employeeDirectory", employees);
        }
    }

}
