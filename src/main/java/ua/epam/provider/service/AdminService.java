package ua.epam.provider.service;


import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.User;

import java.util.ArrayList;
import java.util.List;


public class AdminService {
    public User getAdminInfo( String email) {
        return new UserDao().getUser( email);
    }

    public List<User> showListAdmin() {
        List<User> users = new UserDao().getAllUsers();
        List<User> adminList = new ArrayList<>();
        for (User user : users) {
            if (user.getStatusUser() == 1) {
                adminList.add(user);
            }
        }
        return adminList;
    }

    public List<User> showListUsers()  {
        List<User> users = new UserDao().getAllUsers();
        List<User> usersList = new ArrayList<>();
        for (User user : users) {
            if (user.getStatusUser() == 0) {
                usersList.add(user);
            }
        }
        return usersList;
    }
}