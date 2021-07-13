package ua.epam.provider.servlet.user;

import ua.epam.provider.entity.User;
import ua.epam.provider.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/list-user.do")
public class UserListServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = null;
        userList = userService.showListUser();
        List<User> users = new ArrayList<>();
        for (User user:userList) {
            if (user.getStatusUser()==0&&user.getStatusActive()==1){
                users.add(user);
            }
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/list-user.jsp").forward(
                request, response);
    }
}
