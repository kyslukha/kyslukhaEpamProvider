package ua.epam.provider.servlet.login;


import org.apache.log4j.Logger;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.encryptor.Encryptor;
import ua.epam.provider.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/sign-up.do")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = -6745536593627695341L;

    private static final Logger log = Logger.getLogger(LoginServlet.class);


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        log.info("Sign up do get");
        request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Integer statusUser = 0;
        if (request.getParameter("statusUser") != null) {
            statusUser = 1;
        }
        if (new UserDao().isExistUser(email)||new UserDao().isExistUserPhone(phone)) {
            request.setAttribute("errorMessage", "User with email " + email +
                    " or phone "+phone+" is already exists");
            log.error("errorMessage --> user is exists" + email + " or " + phone);
            request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                    request, response);
        } else if (email.equals("") || password.equals("") || phone.equals("") || name.equals("")) {
            request.setAttribute("errorMessage", "Fields can't be empty");
            log.error("errorMessage --> Fields can't be empty");
            request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                    request, response);
        } else {
            String newPassword = Encryptor.encrypt(password, email);
            User user = new User(name, newPassword, email, phone, statusUser);
            new UserDao().createUser(user);
            log.info("User is created" + email);
            response.sendRedirect("/login.do");
        }
    }
}

