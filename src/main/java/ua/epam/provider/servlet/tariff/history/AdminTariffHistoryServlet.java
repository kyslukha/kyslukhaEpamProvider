package ua.epam.provider.servlet.tariff.history;

import ua.epam.provider.dao.TariffDao;
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

@WebServlet(urlPatterns = "/admin-tariff-history.do")
public class AdminTariffHistoryServlet extends HttpServlet {
    private UserService userService = new UserService();
    private TariffHistoryService userTariff = new TariffHistoryService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");

        User  user = userService.getUserInfo(email);


        List<TariffHistory> listUserTariffHistory = new ArrayList<>();
        listUserTariffHistory = userTariff.showUserTariffHistory(user);

        List<UserTariff> userTariffs = new ArrayList<>();
        List<Tariff> tariffList = new TariffDao().showListTariffs();
        for (Tariff tariff : tariffList) {



            for (TariffHistory userTariff : listUserTariffHistory) {

                if (userTariff.getTariffId().equals(tariff.getId())) {
                    userTariffs.add(new UserTariff(tariff.getTitle(), userTariff.getDateStart(),userTariff.getDateFinish(),userTariff.getId()));
                }
            }
        }
        Collections.sort(userTariffs);
        request.setAttribute("listTariffs", userTariffs);
        request.getRequestDispatcher("/WEB-INF/views/admin-tariff-history.jsp").forward(
                request, response);
    }
}

