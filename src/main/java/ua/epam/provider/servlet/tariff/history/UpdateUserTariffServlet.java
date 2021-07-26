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

@WebServlet(urlPatterns = "/user/update-user-tariff.do")
public class UpdateUserTariffServlet extends HttpServlet {
    private TariffHistoryService userTariffService = new TariffHistoryService();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.getSession().setAttribute("title", title);
        request.getSession().setAttribute("id", id);
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        if (user.getStatusActive().equals(0)) {
            response.sendRedirect("/user.do");
        }else {
            request.getRequestDispatcher("/WEB-INF/views/user/update-user-tariff.jsp").forward(
                    request, response);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        Integer id = (Integer) request.getSession().getAttribute("id");
        LocalDate dateFinish = LocalDate.parse(request.getParameter("dateFinish"));
        TariffHistory userTariff = new TariffHistory();
        userTariff = userTariffService.findById(id);
        if (!userTariff.getDateStart().plusDays(7L).isBefore(dateFinish)) {
            dateFinish = userTariff.getDateStart().plusDays(7L);
        }
        userTariffService.updateUserTariff(userTariff, dateFinish);
        response.sendRedirect("/user/list-user-tariff.do");
    }

}
