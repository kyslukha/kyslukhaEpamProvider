package ua.epam.provider.servlet.tariff;

import com.itextpdf.text.DocumentException;
import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.file.Document;
import ua.epam.provider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = "/delete-tariff.do")
public class DeleteTariffServlet extends HttpServlet {
    private static final String FILE_DIR = "files";
    private static final long serialVersionUID = 1L;

    private TariffService tariffService = new TariffService();
    private TariffDao tariffDao = new TariffDao();
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("");
        String fileName = path + FILE_DIR + File.separator + request.getParameter("file");
        System.out.println("Get file: " + fileName);

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        byte[] bytes = title.getBytes(StandardCharsets.ISO_8859_1);
        title = new String(bytes, StandardCharsets.UTF_8);
        Tariff tariff = tariffDao.getTariff(title);
        tariffService.deleteTariff(tariff);
        List<Tariff> list = tariffDao.showListTariffs();
        try {
            new Document().createPDF(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/tariff-admin.do");
    }
}
