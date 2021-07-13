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

@WebServlet(urlPatterns = "/update-user.do")
public class UpdateUserServlet extends HttpServlet {
    private User oldUser = new User();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        oldUser = new UserDao().getUser( email);
        request.getSession().setAttribute("oldEmail", email);
        request.getSession().setAttribute("oldPhone", oldUser.getPhone());
        request.getSession().setAttribute("oldName", oldUser.getName());
        request.getSession().setAttribute("oldUser", oldUser);
        request.getRequestDispatcher("/WEB-INF/views/update-user.jsp").forward(
                request, response);

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("newName");
        String email = request.getParameter("newEmail");
        String phone = request.getParameter("newPhone");
        String password = request.getParameter("newPassword");
        String oldEmail = (String) request.getSession().getAttribute("oldEmail");
        String newPassword = Encryptor.encrypt(password, email);
        User user = new User(name, email, phone, newPassword);
        oldUser = new UserDao().getUser( oldEmail);
        if (!new UserDao().isExistUser( email) || email.equals(oldEmail)) {
            new UserDao().updateUser( oldUser, user);
            User newUser = new UserDao().getUser(email);
            if (newUser.getStatusUser() == 1) {
                request.getSession().setAttribute("email", user.getEmail());
                request.getSession().setAttribute("name", user.getName());
                request.getSession().setAttribute("phone", user.getPhone());
                response.sendRedirect("/admin.do");
            } else {
                response.sendRedirect("/list-user.do");
            }
        } else {
            request.setAttribute("errorMessage", "User with that  " + email + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/update-user.jsp").forward(
                    request, response);
        }
    }
}
