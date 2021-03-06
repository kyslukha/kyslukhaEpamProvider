package ua.epam.provider.servlet.tariff;


import ua.epam.provider.dao.ServiceDao;
import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.service.TariffService;
import ua.epam.provider.service.TariffServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/admin/add-tariff.do")
public class AddTariffAdminServlet extends HttpServlet {
    private static final String FILE_DIR = "files";
    private static final long serialVersionUID = 1L;
    private TariffService tariffService = new TariffService();
    private ServiceDao serviceDao = new ServiceDao();
    private TariffServiceService tariffServiceService = new TariffServiceService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("listServices", serviceDao.showListServicesTitles());
        request.getRequestDispatcher("/WEB-INF/views/admin/add-tariff.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        Double priceByDay = Double.valueOf(request.getParameter("priceByDay"));
        String[] titles = request.getParameterValues("services");
        List<Service> serviceList = new ArrayList<>();
        for (String serviceTitle : titles) {
            Service service = new ServiceDao().getService(serviceTitle);
            serviceList.add(service);
        }
        Tariff tariff = new Tariff(priceByDay, title);
        if (!new TariffDao().isExistTariff(title)) {
            tariffService.addNewTariff(tariff);
            Tariff newTariff = new TariffDao().getTariff(title);
            tariffServiceService.createTariffServices(newTariff, serviceList);
            response.sendRedirect("/admin/tariff-admin.do");
        } else {
            request.setAttribute("errorMessage", "Service " + tariff.getTitle() + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/admin/add-tariff.jsp").forward(
                    request, response);
        }
    }
}
