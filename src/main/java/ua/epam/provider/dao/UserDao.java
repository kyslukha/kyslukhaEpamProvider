package ua.epam.provider.dao;

import ua.epam.provider.connection.ConnectionPool;
import ua.epam.provider.entity.User;
import ua.epam.provider.interfaces.UserImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserImpl {

    @Override
    public void createUser(User user) {
        String sqlString = "INSERT INTO user (name, password, email, phone, status_user )" +
                " VALUES (?,?,?,?,?)";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getStatusUser());

            int rows = statement.executeUpdate();

            if (rows > 0)
                System.out.println("A new User has been inserted successfully!");
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
    public void updateUser(User oldUser, User user) {
        String sqlString = "UPDATE user  SET email = ?, name = ?, phone = ?, password = ? WHERE Id = ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassword());
            statement.setInt(5, oldUser.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The User with email" + user.getEmail() + "has been updated!");
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
    public void updateUserPassword(String email, String password) {
        if (isExistUser(email)) {
            String sqlString = "UPDATE user  SET password = ?  WHERE email = ?";
            PreparedStatement statement;

            Connection connection = ConnectionPool.getInstance().getConnection();
            //Connection connection = null;

            try {
               // connection = buildConnection();
                statement = connection.prepareStatement(sqlString);
                statement.setString(1, password);
                statement.setString(2, email);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("The password of user with email " + email + " has been updated!");
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

        } else {
            System.out.println("That email not found");
        }
    }

    @Override
    public boolean isExistUser(String email) {
        List<User> users = getAllUsers();
        boolean check = false;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public List<User> getAllUsers() {
        String sqlString = "SELECT * FROM  user";
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8));
                users.add(user);
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
        return users;
    }

    @Override
    public User getUser(String email) {
        List<User> users = getAllUsers();
        User thisUser = new User();
        for (User user : users
        ) {
            if (user.getEmail().equals(email)) {
                thisUser = user;
            }
        }
        System.out.println(thisUser.getId());
        return thisUser;
    }

    @Override
    public void deleteUser(User user) {
        String sqlString = "DELETE FROM user  WHERE email = ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
           // connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, user.getEmail());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("User with email " + user.getEmail() + " has been deleted!");
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
    public void changeActiveStatus(String email, Integer statusActive) {
        String sqlString = "UPDATE user  SET status_active = ? WHERE email = ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
           //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setInt(1, statusActive);
            statement.setString(2, email);

            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The status of user with email" + email + "has been updated!");
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
    public void addMoney(String email, Double sum) {
        String sqlString = "UPDATE user  SET account = ? WHERE email = ?";
        PreparedStatement statement;

        Connection connection = ConnectionPool.getInstance().getConnection();
        //Connection connection = null;

        try {
            //connection = buildConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setDouble(1, sum);
            statement.setString(2, email);

            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("The account of user is equal " + sum + " now");
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
    public User findUserById(Integer id) {
        List<User> userList = getAllUsers();
        User thisUser = new User();
        for (User user : userList) {
            if (user.getId().equals(id)) {
                thisUser = user;
            }
        }
        return thisUser;
    }
}
