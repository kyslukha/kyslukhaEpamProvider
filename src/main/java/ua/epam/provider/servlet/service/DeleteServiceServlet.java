package ua.epam.provider.servlet.service;

import ua.epam.provider.dao.ServiceDao;
import ua.epam.provider.entity.Service;
import ua.epam.provider.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete-service.do")
public class DeleteServiceServlet extends HttpServlet {

    private ServiceService serviceService = new ServiceService();
    private ServiceDao serviceDao = new ServiceDao();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        Service service = serviceDao.getService( title);
        serviceService.deleteService( service);
        response.sendRedirect("/admin/service-admin.do");
    }
}