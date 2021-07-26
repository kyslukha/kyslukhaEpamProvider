package ua.epam.provider.file;

import com.itextpdf.text.DocumentException;
import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.service.TariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
    private static final String FILE_DIR = "files";
    private static final long serialVersionUID = 1L;
    private TariffService tariffService = new TariffService();
    private TariffDao tariffDao = new TariffDao();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("");
        String fileName = path + FILE_DIR + File.separator + request.getParameter("file");
        System.out.println("Get file: " + fileName);
        List<Tariff> list = tariffDao.showListTariffs();
        try {
            new Document().createPDF(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        String filePath = "Tariffs.pdf";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inStream.close();
        outStream.close();
    }
}
