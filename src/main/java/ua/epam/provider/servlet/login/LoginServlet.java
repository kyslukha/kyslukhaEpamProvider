package ua.epam.provider.servlet.login;

import org.apache.log4j.Logger;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private final LoginService userValidationService = new LoginService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("email") == null) {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            if (request.getSession().getAttribute("statusUser") != null) {
                response.sendRedirect(request.getContextPath() + "/admin/admin.do");
                log.info("to admins page ");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/user.do");
                log.info("to users page ");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer statusUser = 0;
        if (request.getParameter("statusUser") != null) {
            statusUser = 1;
        }
        boolean isUserValid = false;
        isUserValid = userValidationService.isUserValid( email, password, statusUser);
        if (isUserValid) {
            User user = new UserDao().getUser( email);
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("phone", user.getPhone());
            request.getSession().setAttribute("statusUser",user.getStatusUser());
            if (statusUser == 1) {
                response.sendRedirect("/admin/admin.do");
                log.info("admin login --> " + email);
            } else {
                request.getSession().setAttribute("account", user.getAccount());
                response.sendRedirect("/user/user.do");
                log.info("user login--> " + email);
            }
        } else if (password.equals("") || email.equals("")) {
            request.setAttribute("errorMessage", "Fields can't be empty");
            log.error("errorMessage --> Fields can't be empty");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
                    request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!");
            log.error("errorMessage --> Invalid Credentials!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
                    request, response);
        }
    }
}
