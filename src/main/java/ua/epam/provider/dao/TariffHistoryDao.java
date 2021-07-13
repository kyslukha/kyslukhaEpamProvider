package ua.epam.provider.dao;

import ua.epam.provider.connection.ConnectionPool;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;
import ua.epam.provider.interfaces.TariffHistoryImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TariffHistoryDao implements TariffHistoryImpl {
    @Override
    public void createUserTariff(TariffHistory tariffHistory) {
        String sqlString = "INSERT INTO tariff_history (date_start,  date_finish, status, user_id, tariff_id)" +
                " VALUES (?,?,?,?,?)";
        PreparedStatement statement;
        Connection connection = ConnectionPool.getInstance().getConnection();

       // Connection connection = null;
        try {
          // connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setDate(1, Date.valueOf(tariffHistory.getDateStart()));
            statement.setDate(2, Date.valueOf(tariffHistory.getDateFinish()));
            statement.setInt(3, tariffHistory.getStatus());
            statement.setInt(4, tariffHistory.getUserId());
            statement.setInt(5, tariffHistory.getTariffId());

            int rows = statement.executeUpdate();

            if (rows > 0)
                System.out.println("A new tariff  for user  has been inserted successfully!");
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
    public void updateUserTariff(TariffHistory tariffHistory, LocalDate dateFinish) {
        String sqlString = "UPDATE tariff_history  SET date_finish = ? WHERE id = ?";
        PreparedStatement statement;
        Connection connection = ConnectionPool.getInstance().getConnection();
       // Connection connection = null;
        try {
           // connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setDate(1, Date.valueOf(dateFinish));
            statement.setInt(2, tariffHistory.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The tariff for user has been updated!");
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
    public void inactiveStatusTariff(User user) {
        List<TariffHistory> userTariffList = showListActiveAndFutureUsersTariffs(user);
        String sqlUpdate = "UPDATE tariff_history  SET status = ? WHERE id = ?";
        String sqlDelete = "DELETE tariff_history  WHERE id = ?";
        //Connection connection = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        for (TariffHistory checkUserTariff : userTariffList) {
            if (checkUserTariff.getDateStart().isAfter(LocalDate.now())) {
                PreparedStatement statement;

                try {
                    //connection = buildConnection();
                    statement = connection.prepareStatement(sqlDelete);
                    statement.setInt(1, checkUserTariff.getId());
                    int rows = statement.executeUpdate();
                    if (rows > 0)
                        System.out.println("Tariff  has been deleted!");
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

            } else {
                PreparedStatement statement;
                try {
                   //connection = buildConnection();
                    statement = connection.prepareStatement(sqlUpdate);
                    statement.setInt(1, 0);
                    statement.setInt(2, checkUserTariff.getId());

                    int rows = statement.executeUpdate();
                    if (rows > 0)
                        System.out.println("Tariff is inactive");
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
        }
    }

    @Override
    public boolean isExistUserTariff(User user, Tariff tariff) {
        List<TariffHistory> tariffs = showListAllTariffs();
        boolean check = false;
        for (TariffHistory checkUserTariff : tariffs) {
            if (checkUserTariff.getTariffId().equals(tariff.getId()) && checkUserTariff.getUserId().equals(user.getId())) {
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public TariffHistory getUserTariff(Tariff tariff, User user) {
        List<TariffHistory> userTariffs = showListAllTariffs();
        TariffHistory thisUserTariff = new TariffHistory();
        for (TariffHistory checkUserTariff : userTariffs) {
            if (checkUserTariff.getTariffId().equals(tariff.getId()) && checkUserTariff.getUserId().equals(user.getId())) {
                thisUserTariff = checkUserTariff;
            }
        }
        return thisUserTariff;
    }

    @Override
    public List<TariffHistory> showListAllTariffs() {
        String sqlString = "SELECT * FROM  tariff_history";
        List<TariffHistory> userTariffs = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;
        try {
            //connection = buildConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                TariffHistory tariffHistory = new TariffHistory(resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6));
                userTariffs.add(tariffHistory);
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
        return userTariffs;
    }

    @Override
    public List<TariffHistory> showListAllActiveTariffs() {
        List<TariffHistory> tariffs = showListAllTariffs();
        List<TariffHistory> activeTariffs = new ArrayList<>();
        for (TariffHistory tariff : tariffs) {
            if (tariff.getStatus() == 1) {
                activeTariffs.add(tariff);
            }
        }
        return activeTariffs;
    }

    @Override
    public List<TariffHistory> showListUsersTariffHistories(User user) {
        List<TariffHistory> userTariffs = showListAllTariffs();
        List<TariffHistory> thisUserTariffs = new ArrayList<>();
        for (TariffHistory userTariff : userTariffs) {
            if (userTariff.getUserId().equals(user.getId())) {
                thisUserTariffs.add(userTariff);
            }
        }

        return thisUserTariffs;
    }

    @Override
    public List<TariffHistory> showListActiveAndFutureUsersTariffs(User user) {
        List<TariffHistory> userTariffs = showListUsersTariffHistories(user);
        List<TariffHistory> thisUserTariffs = new ArrayList<>();
        for (TariffHistory userTariff : userTariffs) {

            if (userTariff.getDateStart().isAfter(LocalDate.now()) || userTariff.getStatus().equals(1)) {
                thisUserTariffs.add(userTariff);
            }
        }

        return thisUserTariffs;
    }

    @Override
    public void activeStatusTariff(User user) {
        List<TariffHistory> userTariffList = showListUsersTariffHistories(user);
        String sqlUpdate = "UPDATE tariff_history  SET status = ? WHERE id = ?";
        //Connection connection = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        for (TariffHistory checkUserTariff : userTariffList) {
            if (validationUserTariff(checkUserTariff)) {
                PreparedStatement statement;
                System.out.println(checkUserTariff.getDateStart());
                System.out.println(checkUserTariff.getDateFinish());




                try {
                   // connection = buildConnection();
                    statement = connection.prepareStatement(sqlUpdate);
                    statement.setInt(1, 1);
                    statement.setInt(2, checkUserTariff.getId());
                    int rows = statement.executeUpdate();
                    if (rows > 0)
                        System.out.println("Tariff  has been active!");
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

        }
    }

    @Override
    public TariffHistory findById(Integer id) {
        List<TariffHistory> tariffHistoryList = showListAllTariffs();
        TariffHistory thisUserTariff = new TariffHistory();
        for (TariffHistory userTariff : tariffHistoryList) {
            if (userTariff.getId().equals(id)) {
                thisUserTariff = userTariff;
            }
        }
        return thisUserTariff;
    }

    @Override
    public void deleteUserTariff(TariffHistory tariffHistory) {
        String sqlString = "DELETE FROM tariff_history  WHERE id= ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setInt(1, tariffHistory.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("User tariff with id " + tariffHistory.getId() + " has been deleted!");
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
    public boolean validationUserTariff(TariffHistory tariffHistory) {

        return (!tariffHistory.getDateStart().isAfter(LocalDate.now())) &&
                (!tariffHistory.getDateFinish().isBefore(LocalDate.now()));
    }

    @Override
    public void checkStatusTariff(TariffHistory tariffHistory) {
        String sqlString = "UPDATE tariff_history  SET status = ? WHERE id = ?";
        PreparedStatement statement;
        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;
        try {
           //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setInt(1, tariffHistory.getStatus());
            statement.setInt(2, tariffHistory.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The status of tariff for user has been updated!");
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
}
