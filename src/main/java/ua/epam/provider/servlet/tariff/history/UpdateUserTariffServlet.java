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
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/update-user-tariff.do")
public class UpdateUserTariffServlet extends HttpServlet {
    private TariffHistoryService userTariffService = new TariffHistoryService();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        String title = request.getParameter("title");
        System.out.println(title);
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        byte[] bytes = title.getBytes(StandardCharsets.ISO_8859_1);
        title = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(title);
        request.getSession().setAttribute("title", title);
        request.getSession().setAttribute("id", id);
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        if (user.getStatusActive().equals(0)) {
            response.sendRedirect("/user.do");
        }else {
            request.getRequestDispatcher("/WEB-INF/views/update-user-tariff.jsp").forward(
                    request, response);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        Integer id = (Integer) request.getSession().getAttribute("id");
        System.out.println(id);
        LocalDate dateFinish = LocalDate.parse(request.getParameter("dateFinish"));
        System.out.println(id);
        TariffHistory userTariff = new TariffHistory();
        userTariff = userTariffService.findById(id);
        System.out.println(id);
        System.out.println(userTariff.getDateStart());
        if (!userTariff.getDateStart().plusDays(7L).isBefore(dateFinish)) {
            dateFinish = userTariff.getDateStart().plusDays(7L);
        }

        userTariffService.updateUserTariff(userTariff, dateFinish);

        response.sendRedirect("/list-user-tariff.do");
    }

}
