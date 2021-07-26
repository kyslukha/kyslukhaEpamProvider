package ua.epam.provider.servlet.service;

import ua.epam.provider.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/service-admin.do")
public class ServiceAdminServlet extends HttpServlet {
    private ServiceService serviceService = new ServiceService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("services", serviceService.showListServices());
        request.getRequestDispatcher("/WEB-INF/views/admin/service-admin.jsp").forward(
                request, response);
    }
}
