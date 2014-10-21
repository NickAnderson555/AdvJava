package java112.project4;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet (
    name = "JSTLLab1Servlet",
    urlPatterns = { "/JSTLlab1", "/lab41" }
)

public class JSTLLab1Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        List list = new ArrayList<String>();
        list.add("butter");
        list.add("milk");
        list.add("cookies");
        list.add("rhino blood");
        list.add("monies");

        request.setAttribute("list", list);

        String url = "/jstl-lab2.jsp";

        RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
    }
}
