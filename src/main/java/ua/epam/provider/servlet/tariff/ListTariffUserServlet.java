package ua.epam.provider.servlet.tariff;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/list-tariff-user.do")
public class ListTariffUserServlet extends HttpServlet {
    private TariffDao tariffDao = new TariffDao();

    private final String sqlString = "SELECT * FROM  tariff";
    private final String sqlString_TITLE_DESC = "SELECT * FROM  tariff ORDER BY title DESC";
    private final String sqlString_TITLE_ASC = "SELECT * FROM  tariff ORDER BY title";
    private final String sqlString_PRICE_BY_DAY_DESC = "SELECT * FROM  tariff ORDER BY price_by_day DESC";
    private final String getSqlString_PRICE_BY_DAY_ASC = "SELECT * FROM  tariff ORDER BY price_by_day";


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String string = sqlString;
        if (request.getParameter("sortTitleASC") != null) {
            string = sqlString_TITLE_ASC;
            request.setAttribute("sortTitleASC", null);
        }
        if (request.getParameter("sortTitleDESC") != null) {
            string = sqlString_TITLE_DESC;
            System.out.println("sortTitleDESC not null");
            request.setAttribute("sortTitleDESC", null);
        }
        if (request.getParameter("sortPriceByDayASC") != null) {
            string = getSqlString_PRICE_BY_DAY_ASC;
            request.setAttribute("sortPriceByDayASC", null);
        }
        if (request.getParameter("sortPriceByDayDESC") != null) {
            string = sqlString_PRICE_BY_DAY_DESC;
            request.setAttribute("sortPriceByDayDESC", null);
        }
        List<Tariff> tariffList = tariffDao.listTariffs(string);

        request.setAttribute("tariffs", tariffList);
        request.getRequestDispatcher("/WEB-INF/views/list-tariff-user.jsp").forward(
                request, response);
    }
}
