package ua.epam.provider.dao;

import ua.epam.provider.connection.ConnectionPool;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.interfaces.TariffImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.epam.provider.connection.DBConnector.buildConnection;

public class TariffDao implements TariffImpl {


    @Override
    public void createTariff(Tariff tariff) {
        String sqlString = "INSERT INTO tariff (price_by_day, title)" +
                " VALUES (?, ?)";
        PreparedStatement statement;

        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = null;

        try {
            connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setDouble(1, tariff.getPriceByDay());
            statement.setString(2, tariff.getTitle());

            int rows = statement.executeUpdate();

            if (rows > 0)
                System.out.println("A new Tariff " + tariff.getTitle() + " has been inserted successfully!");
            else
                System.out.println("Something went wrong with creation!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTariff(Tariff oldTariff, Tariff tariff) {
        String sqlString = "UPDATE tariff  SET price_by_day = ?, title = ? WHERE Id = ?";
        PreparedStatement statement;

        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = null;

        try {
           connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setDouble(1, tariff.getPriceByDay());
            statement.setString(2, tariff.getTitle());
            statement.setInt(3, oldTariff.getId());

            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The Tariff with title " + oldTariff.getTitle() + "has been updated!");
            else
                System.out.println("Something went wrong with updating!");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTariff(Tariff tariff) {
        String sqlString = "DELETE FROM tariff  WHERE title= ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, tariff.getTitle());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("Tariff with title " + tariff.getTitle() + " has been deleted!");
            else
                System.out.println("Something went wrong with deleting!");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Tariff getTariff(String title) {
        List<Tariff> listTariffs = showListTariffs();
        Tariff thisTariff = new Tariff();
        for (Tariff tariff : listTariffs) {
            if (tariff.getTitle().equals(title)) {
                thisTariff = tariff;
            }
        }
        return thisTariff;
    }

    @Override
    public boolean isExistTariff(String title) {
        List<Tariff> listTariffs = showListTariffs();
        boolean check = false;
        for (Tariff tariff : listTariffs) {
            if (tariff.getTitle().equals(title)) {
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public List<Tariff> showListTariffs() {
        String sqlString = "SELECT * FROM  tariff ORDER BY title";
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
           // connection = buildConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                Tariff tariff = new Tariff(resultSet.getInt(1),
                        resultSet.getDouble(2), resultSet.getString(3));
                tariffs.add(tariff);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public List<String> showListTariffTitles() {
        List<Tariff> listTariffs = showListTariffs();
        List<String> titles = new ArrayList<>();
        for (Tariff tariff : listTariffs) {
            titles.add(tariff.getTitle());
        }
        return titles;
    }

    @Override
    public Tariff findTariffById(Integer id) {
        List<Tariff> tariffList = showListTariffs();
        Tariff thisTariff = new Tariff();
        for (Tariff tariff : tariffList) {
            if (tariff.getId().equals(id)) {
                thisTariff = tariff;
            }
        }
        return thisTariff;
    }

    @Override
    public List<Tariff> listTariffs(String sqlString) {
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
       // Connection connection = null;

        try {
            //connection = buildConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                Tariff tariff = new Tariff(resultSet.getInt(1),
                        resultSet.getDouble(2), resultSet.getString(3));
                tariffs.add(tariff);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tariffs;
    }



}
