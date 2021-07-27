package ua.epam.provider.servlet.user;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.dao.TariffHistoryDao;
import ua.epam.provider.dao.TariffServiceDao;
import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.UserService;
import ua.epam.provider.user.info.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/admin/user-list-info.do")
public class UserListInfoServlet  extends HttpServlet {
    private UserService userService = new UserService();
private TariffHistoryDao tariffHistoryDao = new TariffHistoryDao();
private TariffDao tariffDao = new TariffDao();
private TariffServiceDao tariffServiceDao = new TariffServiceDao();


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        LocalDate start = LocalDate.parse(request.getParameter("start"));
        LocalDate finish = LocalDate.parse(request.getParameter("finish"));

        System.out.println(start);
        System.out.println(finish);
        request.getSession().setAttribute("start", start);
        request.getSession().setAttribute("finish", finish);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

//        LocalDate start = LocalDate.parse(request.getParameter("start"));
//        LocalDate finish = LocalDate.parse(request.getParameter("finish"));
        List<User> userList = userService.showListUser();
        List<UserInfo> userInfoList = new ArrayList<>();
        for (User user: userList) {
         List<TariffHistory>  tariffHistories =  tariffHistoryDao.showListUsersTariffHistories(user);
            for (TariffHistory tariffHistory: tariffHistories) {
                Tariff tariff = tariffDao.findTariffById(tariffHistory.getTariffId());
                Integer status= tariffHistory.getStatus();
                String statusTariff = "Активный";
                if (status == 0)
                statusTariff = "Неактивный";
                List<Service> services = tariffServiceDao.findAllServicesForTariff(tariff);
                List<String> serviceTitles = new ArrayList<>();
                for (Service service: services) {
                  serviceTitles.add(service.getTitle());
                }

                UserInfo userInfo = new UserInfo(user.getId(), user.getEmail(), tariff.getTitle(),
                        serviceTitles, tariff.getPriceByDay(), statusTariff, tariffHistory.getDateStart());
                userInfoList.add(userInfo);
            }
        }
        request.setAttribute("list", userInfoList);
        request.getRequestDispatcher("/WEB-INF/views/admin/user-list-info.jsp").forward(
                request, response);
    }
}
