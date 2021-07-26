package ua.epam.provider.servlet.user;

import ua.epam.provider.dao.UserDao;
import ua.epam.provider.encryptor.Encryptor;
import ua.epam.provider.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/change-password-user.do")
public class ChangePasswordUserServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/user/change-password-user.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String password =request.getParameter("password");
        String email = (String) request.getSession().getAttribute("email");
        User user = new UserDao().getUser( email);
        String newPassword = Encryptor.encrypt(password, email);
        String oldPassword = user.getPassword();
        if (!newPassword.equals(oldPassword)) {
            new UserDao().updateUserPassword( email, newPassword);
            request.getSession().removeAttribute("user");
            response.sendRedirect("/user/user.do");
        }
        else {
            request.setAttribute("errorMessage", "You input old password, password is not changed ");
            request.getRequestDispatcher("/WEB-INF/views/user/user.jsp").forward(
                    request, response);
        }
    }
}
