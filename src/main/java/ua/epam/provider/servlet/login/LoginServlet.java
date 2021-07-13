package ua.epam.provider.servlet.login;

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

    private final LoginService userValidationService = new LoginService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("email") == null) {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            if ((Integer.parseInt((String) request.getSession().getAttribute("status_user")) == 1)) {
                response.sendRedirect(request.getContextPath() + "/admin.do");
            } else {
                response.sendRedirect(request.getContextPath() + "/user.do");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer statusUser = 0;
        if (request.getParameter("status_user") != null) {
            statusUser = 1;
        }
        boolean isUserValid = false;
        isUserValid = userValidationService.isUserValid( email, password, statusUser);
        if (isUserValid) {
            User user = new UserDao().getUser( email);
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("phone", user.getPhone());
            if (statusUser == 1) {
                response.sendRedirect("/admin.do");
            } else {
                request.getSession().setAttribute("account", user.getAccount());
                response.sendRedirect("/user.do");
            }
        } else if (password.equals("") || email.equals("")) {
            request.setAttribute("errorMessage", "Fields can't be empty");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
                    request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
                    request, response);
        }
    }
}
