package ua.epam.provider.dao;

import ua.epam.provider.connection.DBManager;
import ua.epam.provider.entity.Service;
import ua.epam.provider.interfaces.ServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements ServiceImpl {

    @Override
    public void createService(String title) {
        String sqlString = "INSERT INTO service (title ) VALUES (?)";
        PreparedStatement statement = null;
        Connection connection = null;
//       Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, title);
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("Добавлена новая услуга" + title);
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

    @Override
    public void deleteService(Service service) {
        String sqlString = "DELETE FROM service  WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
//       Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setInt(1, service.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("Service with title " + service.getTitle() + " has been deleted!");
            else
                System.out.println("Something went wrong with deleting!");
            statement.close();
        } catch (SQLException sqlException) {
            DBManager.getInstance().rollbackAndClose(connection);
            sqlException.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    @Override
    public void updateService(Service oldService, Service service) {
        String sqlString = "UPDATE service  SET title = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
//        Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, service.getTitle());
            statement.setInt(2, oldService.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The Service with title" + oldService.getTitle() + "has been updated!");
            else
                System.out.println("Something went wrong with updating!");
            statement.close();
        } catch (SQLException sqlException) {
            DBManager.getInstance().rollbackAndClose(connection);
            sqlException.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }


    @Override
    public List<Service> showListServices() {
        String sqlString = "SELECT * FROM  service";
        List<Service> serviceList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
//        Connection connection = DBManager.getInstance().getConnectionWithDriverManager();
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                Service service = new Service(resultSet.getInt(1), resultSet.getString(2));
                serviceList.add(service);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException sqlException) {
            DBManager.getInstance().rollbackAndClose(connection);
            System.out.println(sqlException.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return serviceList;
    }

    @Override
    public boolean isExistService(String title) {
        List<Service> serviceList = showListServices();
        boolean result = false;
        for (Service service : serviceList) {
            if (service.getTitle().equals(title)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Service getService(String title) {
        List<Service> serviceList = showListServices();
        Service thisService = new Service();
        for (Service service : serviceList) {
            if (service.getTitle().equals(title)) {
                thisService = service;
            }
        }
        return thisService;
    }

    @Override
    public List<String> showListServicesTitles() {
        List<Service> serviceList = showListServices();
        List<String> titles = new ArrayList<>();
        for (Service service : serviceList) {
            titles.add(service.getTitle());
        }
        return titles;
    }

    @Override
    public Service findServiceById(Integer id) {
        List<Service> serviceList = showListServices();
        Service thisService = new Service();
        for (Service service : serviceList) {
            if (service.getId().equals(id)) {
                thisService = service;
            }
        }
        return thisService;
    }
}
