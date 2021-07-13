package ua.epam.provider.servlet.service;

import ua.epam.provider.dao.ServiceDao;
import ua.epam.provider.entity.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/update-service.do")
public class UpdateServiceServlet extends HttpServlet {
    private ServiceDao serviceDao = new ServiceDao();

    private Service oldService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        byte[] bytes = title.getBytes(StandardCharsets.ISO_8859_1);
        title = new String(bytes, StandardCharsets.UTF_8);
        oldService = new ServiceDao().getService( title);
        request.getSession().setAttribute("title", title);
        request.getSession().setAttribute("oldService", oldService);
        request.getRequestDispatcher("/WEB-INF/views/update-service.jsp").forward(
                request, response);

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("newTitle");
        Service service = new Service(title);
        String oldTitle = (String) request.getSession().getAttribute("title");
        if (!new ServiceDao().isExistService( title)) {
            oldService = new ServiceDao().getService(oldTitle);
            serviceDao.updateService( oldService, service);
            request.getSession().removeAttribute("service");
            response.sendRedirect("/service-admin.do");
        } else {
            request.setAttribute("errorMessage", "Service with title  " + title + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/update-service.jsp").forward(
                    request, response);
        }
    }
}
