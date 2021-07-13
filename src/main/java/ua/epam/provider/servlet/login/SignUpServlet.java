package ua.epam.provider.servlet.login;



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

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //Connection connection = buildConnection();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        System.out.println(phone);
       Integer statusUser = 0;
       if (request.getParameter("status_user") != null){
           statusUser =1;
       }

        System.out.println(statusUser);
        if (new UserDao().isExistUser(email)) {
            request.setAttribute("errorMessage", "User with email " + email + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                    request, response);
        } else if (email.equals("") || password.equals("")) {
            request.setAttribute("errorMessage", "Fields can't be empty");
            request.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(
                    request, response);
        } else {
            String newPassword = Encryptor.encrypt(password, email);
            User user = new User(name, newPassword, email, phone, statusUser);
            new UserDao().createUser(user);
            response.sendRedirect("/admin.do");
        }
    }
}

