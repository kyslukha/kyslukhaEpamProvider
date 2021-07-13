package ua.epam.provider.servlet.tariff;

import com.itextpdf.text.DocumentException;
import ua.epam.provider.dao.ServiceDao;
import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.file.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = "/update-tariff.do")
public class UpdateTariffAdminServlet extends HttpServlet {
    private static final String FILE_DIR = "files";
    private static final long serialVersionUID = 1L;
    private ServiceDao serviceDao = new ServiceDao();
    private TariffDao tariffDao = new TariffDao();
    private Tariff oldTariff;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        byte[] bytes = title.getBytes(StandardCharsets.ISO_8859_1);
        title = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(title);
        Double priceByDay = Double.valueOf(request.getParameter("priceByDay"));
        oldTariff = new TariffDao().getTariff(title);
        request.getSession().setAttribute("title", title);
        request.getSession().setAttribute("priceByDay", priceByDay);
        request.getSession().setAttribute("oldTariff", oldTariff);
        request.getRequestDispatcher("/WEB-INF/views/update-tariff.jsp").forward(
                request, response);

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("");
        String fileName = path + FILE_DIR + File.separator + request.getParameter("file");
        System.out.println("Get file: " + fileName);

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("newTitle");
        Double priceByDay = Double.valueOf(request.getParameter("newPriceByDay"));
        Tariff newTariff = new Tariff(priceByDay, title);
        String oldTitle = (String) request.getSession().getAttribute("title");
        if (!new TariffDao().isExistTariff(title)) {
            oldTariff = new TariffDao().getTariff(oldTitle);
            tariffDao.updateTariff(oldTariff, newTariff);
            List<Tariff> list = tariffDao.showListTariffs();
            try {
                new Document().createPDF(list);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            request.getSession().removeAttribute("tariff");
            response.sendRedirect("/tariff-admin.do");
        } else {
            request.setAttribute("errorMessage", "Tariff with title " + title + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/update-tariff.jsp").forward(
                    request, response);
        }
    }
}
