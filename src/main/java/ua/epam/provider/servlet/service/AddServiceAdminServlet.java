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

@WebServlet(urlPatterns = "/add-service.do")
public class AddServiceAdminServlet extends HttpServlet {
    private ServiceService serviceService = new ServiceService();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-service.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        Service service = new Service(title);

        if (!new ServiceDao().isExistService( title)) {
            serviceService.addNewService(title);
            response.sendRedirect("/service-admin.do");
        } else {
            request.setAttribute("errorMessage", "Service " + service.getTitle() + " is already exists");
            request.getRequestDispatcher("/WEB-INF/views/add-service.jsp").forward(
                    request, response);
        }
    }
}
