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

@WebServlet(urlPatterns = "/admin/list-user.do")
public class UserListServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<User> userList = userService.showListUser();
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            if (user.getStatusUser() == 0 && user.getStatusActive() == 1) {
                users.add(user);
            }
        }
        int numberRecords = users.size();
        int numberPages = (int) Math.ceil(numberRecords*1.0/recordsPerPage);
        List<User> list = userService.viewAllUsersPerPage(page,
                recordsPerPage, users);
        System.out.println(page);
        System.out.println(numberPages);
        System.out.println(list.size());
        System.out.println(numberRecords);
        request.setAttribute("numberPages", numberPages);
        request.setAttribute("users", list);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/WEB-INF/views/admin/list-user.jsp").forward(
                request, response);
    }
}
