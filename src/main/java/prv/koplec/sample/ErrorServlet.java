package prv.koplec.sample;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sample/ErrorServlet")
public class ErrorServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = this.getServletContext();

        String sp = System.getProperty("line.separator");

        StringBuffer buf = new StringBuffer();
        buf.append(new java.util.Date());
        buf.append(sp);
        buf.append(req.getAttribute("jakarta.servlet.error.request_uri"));
        buf.append(sp);
        buf.append(req.getAttribute("jakarta.servlet.error.message"));
        application.log(buf.toString());
        resp.sendRedirect(req.getContextPath()+"/error.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
