package ua.epam.provider.servlet.user;

import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.User;
import ua.epam.provider.service.UserService;
import ua.epam.provider.timer.Check;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-money-user.do")
public class AddMoneyUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-money-user.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Double sum = Double.valueOf(request.getParameter("sum"));

        String email = (String) request.getSession().getAttribute("email");
        User  user = new UserDao().getUser( email);
        if (sum > 0) {
            Double account = sum + user.getAccount();
            userService.addMoney( email, account);
            new Check().checkUser(user);
            request.getSession().setAttribute("status", user.getStatusActive());
            request.getSession().setAttribute("account", account);
            request.getSession().removeAttribute("user");
            response.sendRedirect("/user.do");
        }
        else {
            request.setAttribute("errorMessage", "Sum  " + sum + " is negative");
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(
                    request, response);
        }
    }
}
