package prv.koplec.dp.mvc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListController extends HttpServlet{
    public static final String LAST_PARAM = "last";
    public static final String FIRST_PARAM = "first";
    public static final String EMAIL_PARAM = "email";
    public static final String MAILINGBEAN_ATTR = "mailingbean";

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String last = req.getParameter(LAST_PARAM);
        String first = req.getParameter(FIRST_PARAM);
        String email = req.getParameter(EMAIL_PARAM);

        MailingBean mb = MailingBeanFactory.newInstance();

        mb.setLast(last);
        mb.setFirst(first);
        mb.setEmail(email);

        req.setAttribute(MAILINGBEAN_ATTR, mb);

        // beanにビジネスロジックを実行させる
        boolean result = mb.doSubscribe();

        String nextPage = "/success.jsp";
        if(!result) nextPage = "/failure.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(req, resp);
    }
}
