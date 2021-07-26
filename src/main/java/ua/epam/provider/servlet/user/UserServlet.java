package ua.epam.provider.servlet.user;


import ua.epam.provider.entity.User;
import ua.epam.provider.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/user/user.do")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String status = "Активный";
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = userService.getUserInfo(email);
        if (user.getStatusActive().equals(0)){
            status = "Заблокирован";
        }
        Double account = user.getAccount();
        request.setAttribute("account",account);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/WEB-INF/views/user/user.jsp").forward(
                request, response);
    }
}
