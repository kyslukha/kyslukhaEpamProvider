package ua.epam.provider.servlet.tariff.history;

import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.TariffHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/user/close-user-tariff.do")
public class CloseUserTariffHistoryServlet extends HttpServlet {

    private TariffHistoryService userTariff = new TariffHistoryService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        Integer id = Integer.valueOf(request.getParameter("id"));
        TariffHistory tariffHistory = userTariff.findById(id);
        if (user.getStatusActive().equals(1)) {
            if (user.getStatusActive().equals(1)) {
            if (tariffHistory.getStatus() == 0) {
                userTariff.deleteUserTariff(tariffHistory);
            } else if (tariffHistory.getDateStart().plusDays(7L).isBefore(LocalDate.now())) {
                LocalDate dateFinish = LocalDate.now();
                userTariff.updateUserTariff(tariffHistory, dateFinish);
            } else {
                System.out.println("FinishDate is start +7");
                LocalDate dateFinish = tariffHistory.getDateStart().plusDays(7L);
                userTariff.updateUserTariff(tariffHistory, dateFinish);
            }
        }
        response.sendRedirect("/user/list-user-tariff.do");
    }
    else
    {
        response.sendRedirect("/user/user.do");
    }
}
}