package ua.epam.provider.servlet.tariff.history;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.Tariff;
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

@WebServlet(urlPatterns = "/add-user-tariff.do")
public class AddUserTariffServlet extends HttpServlet {
    private TariffHistoryService userTariffService = new TariffHistoryService();
    private TariffDao tariffDao = new TariffDao();
    private UserDao userDao = new UserDao();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        byte[] bytes = title.getBytes(StandardCharsets.ISO_8859_1);
        title = new String(bytes, StandardCharsets.UTF_8);
        String email = String.valueOf(request.getSession().getAttribute("email"));
        User user = new UserDao().getUser(email);
        if (user.getStatusActive().equals(0)) {
            response.sendRedirect("/user.do");
        } else {
            request.getSession().setAttribute("title", title);
            request.getRequestDispatcher("/WEB-INF/views/add-user-tariff.jsp").forward(
                    request, response);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"));
        LocalDate dateFinish = LocalDate.parse(request.getParameter("dateFinish"));
        if (request.getParameter("dateFinish") != null) {
            dateFinish = LocalDate.parse(request.getParameter("dateFinish"));
        }
        request.setCharacterEncoding("UTF-8");
        String email = (String) request.getSession().getAttribute("email");
        String title = (String) request.getSession().getAttribute("title");
        System.out.println(title);

        User user = new UserDao().getUser(email);
        Tariff tariff = new TariffDao().getTariff(title);
        System.out.println(tariff.getId());
        System.out.println(user.getId());
        TariffHistory userTariff = new TariffHistory(tariff.getId(), user.getId());
        userTariff.setDateStart(dateStart);


        userTariff.setDateFinish(dateFinish, dateStart);
        userTariff.setStatus(dateStart, dateFinish);


        if ((userTariffService.validation(user, userTariff)) && (user.getStatusActive().equals(1))) {
            if (!userTariff.getDateStart().isEqual(LocalDate.now())) {
                userTariffService.createUserTariff(userTariff);
                response.sendRedirect("/list-user-tariff.do");
            } else if (tariffDao.findTariffById(userTariff.getTariffId()).getPriceByDay() <= user.getAccount()) {
                Double sum = user.getAccount() - tariffDao.findTariffById(userTariff.getTariffId()).getPriceByDay();
                userTariff.setStatus(1);
                userTariffService.createUserTariff(userTariff);
                userDao.addMoney(user.getEmail(), sum);
                response.sendRedirect("/list-user-tariff.do");
            } else {
                System.out.println("Impossible to create users tariff with that parameters");
                request.setAttribute("errorMessage", "Impossible to create users tariff with that parameters");
                request.getRequestDispatcher("/WEB-INF/views/add-user-tariff.jsp").forward(
                        request, response);

            }
        } else {
            System.out.println("Impossible to create users tariff with that parameters");
            request.setAttribute("errorMessage", "Impossible to create users tariff with that parameters");
            request.getRequestDispatcher("/WEB-INF/views/add-user-tariff.jsp").forward(
                    request, response);
        }
    }
}
