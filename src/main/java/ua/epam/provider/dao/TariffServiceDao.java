package ua.epam.provider.dao;

import ua.epam.provider.connection.DBManager;
import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffService;
import ua.epam.provider.interfaces.TariffServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffServiceDao implements TariffServiceImpl {
    @Override
    public List<Service> findAllServicesForTariff(Tariff tariff) {
        List<TariffService> tariffServices = showListAll();
        List<Service> serviceList = new ArrayList<>();
        for (TariffService tariffService : tariffServices) {
            if (tariffService.getTariffId().equals(tariff.getId())) {
                serviceList.add(new ServiceDao().findServiceById(tariffService.getServiceId()));
            }
        }
        return serviceList;
    }

    @Override
    public List<Tariff> findAllTariffsForService(Service service) {
        List<TariffService> tariffServices = showListAll();
        List<Tariff> tariffList = new ArrayList<>();
        for (TariffService tariffService : tariffServices) {
            if (tariffService.getServiceId().equals(service.getId())) {
                tariffList.add(new TariffDao().findTariffById(tariffService.getTariffId()));
            }
        }
        return tariffList;
    }

    @Override
    public List<TariffService> showListAll() {
        String sqlString = "SELECT * FROM  tariff_service";
        List<TariffService> tariffServicesList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
//        Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                TariffService tariffService = new TariffService(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
                tariffServicesList.add(tariffService);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException sqlException) {
            DBManager.getInstance().rollbackAndClose(connection);
            System.out.println(sqlException.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return tariffServicesList;
    }

    @Override
    public void createTariffServices(Tariff tariff, List<Service> serviceList) {
        String sqlString = "INSERT INTO tariff_service (tariff_id, service_id)" +
                " VALUES (?, ?)";
        for (Service service : serviceList) {
            PreparedStatement statement = null;
            Connection connection = null;
//            Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
            try {
                connection = DBManager.getInstance().getConnection();
                statement = connection.prepareStatement(sqlString);
                statement.setInt(1, tariff.getId());
                statement.setInt(2, service.getId());
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("Service of tariff has been inserted successfully!");
                else
                    System.out.println("Something went wrong with creation!");
                statement.close();
            } catch (SQLException sqlException) {
                DBManager.getInstance().rollbackAndClose(connection);
                sqlException.printStackTrace();
            } finally {
                DBManager.getInstance().commitAndClose(connection);
            }
        }
    }
}
