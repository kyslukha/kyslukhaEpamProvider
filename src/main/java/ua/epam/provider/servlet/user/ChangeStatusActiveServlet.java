package ua.epam.provider.servlet.user;

import ua.epam.provider.dao.TariffHistoryDao;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.UserService;
import ua.epam.provider.timer.Check;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/change-status-user.do")
public class ChangeStatusActiveServlet extends HttpServlet {
    private UserService userService = new UserService();
    private TariffHistoryDao userTariff = new TariffHistoryDao();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = new UserDao().getUser(email);
        Integer statusActive = user.getStatusActive();
        if (statusActive == 1) {
            statusActive = 0;
            userService.changeStatusActive(user, statusActive);
            userTariff.inactiveStatusTariff(user);
            response.sendRedirect("/admin/list-inactive-user.do");
        } else {
            statusActive = 1;
            userService.changeStatusActive(user, statusActive);
            new Check().checkActiveUser(user);
            response.sendRedirect("/admin/list-user.do");
        }
    }
}