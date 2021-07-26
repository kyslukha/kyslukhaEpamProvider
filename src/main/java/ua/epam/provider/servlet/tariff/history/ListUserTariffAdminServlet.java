package ua.epam.provider.servlet.tariff.history;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.TariffHistoryService;
import ua.epam.provider.service.UserService;
import ua.epam.provider.user.tariff.UserTariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/admin/list-user-tariff-admin.do")
public class ListUserTariffAdminServlet extends HttpServlet {
    private UserService userService = new UserService();
    private TariffHistoryService userTariff = new TariffHistoryService();
    private UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        User user = userDao.getUser( email);
        List<UserTariff> userTariffs = new ArrayList<>();
        List<TariffHistory>   listUserTariffs = userTariff.showActiveAndFutureUserTariff( user);
        for (TariffHistory tariffHistory : listUserTariffs) {
            Tariff tariff = new TariffDao().findTariffById( tariffHistory.getTariffId());
            String status = "активный";
            if (tariffHistory.getStatus().equals(0)){
                status = "неактивный";
            }
            userTariffs.add(new UserTariff(tariff.getTitle(), tariffHistory.getDateStart(),
                    tariffHistory.getDateFinish(),tariffHistory.getId(),status));
            System.out.println(tariffHistory.getDateStart());
        }
        Collections.sort(userTariffs);
        request.setAttribute("listTariffs", userTariffs);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/admin/list-user-tariff-admin.jsp").forward(
                request, response);
    }
}
