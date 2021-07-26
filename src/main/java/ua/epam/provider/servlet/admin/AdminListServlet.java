package ua.epam.provider.servlet.admin;


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

@WebServlet(urlPatterns = "/admin/list-admin.do")
public class AdminListServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.showListUser();
        List<User>adminList = new ArrayList<>();
        for (User user:userList) {
           if (user.getStatusUser()==1){
               adminList.add(user);
           }
        }
        request.setAttribute("admins", adminList);
        request.getRequestDispatcher("/WEB-INF/views/admin/list-admin.jsp").forward(
                request, response);
    }
}
