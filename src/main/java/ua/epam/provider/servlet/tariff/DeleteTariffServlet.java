package ua.epam.provider.servlet.tariff;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete-tariff.do")
public class DeleteTariffServlet extends HttpServlet {
    private static final String FILE_DIR = "files";
    private static final long serialVersionUID = 1L;

    private TariffService tariffService = new TariffService();
    private TariffDao tariffDao = new TariffDao();
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        Tariff tariff = tariffDao.getTariff(title);
        tariffService.deleteTariff(tariff);

        response.sendRedirect("/admin/tariff-admin.do");
    }
}
