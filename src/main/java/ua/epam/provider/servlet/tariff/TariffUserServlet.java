package ua.epam.provider.servlet.tariff;

import ua.epam.provider.dao.TariffServiceDao;
import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/user/tariff-user.do")
public class TariffUserServlet extends HttpServlet {
    private TariffService tariffService = new TariffService();
    private TariffServiceDao tariffServiceDao = new TariffServiceDao();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<Tariff> tariffList = tariffService.showListTariff();
        Map<Tariff, List<Service>> tariffListMap = new HashMap<>();
        for (Tariff tariff : tariffList) {
            List<Service> serviceList = tariffServiceDao.findAllServicesForTariff( tariff);
            tariffListMap.put(tariff, serviceList);
        }

        request.setAttribute("tariffs", tariffListMap);
        request.getRequestDispatcher("/WEB-INF/views/user/tariff-user.jsp").forward(
                request, response);
    }
}
