package ua.epam.provider.service;

import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.User;

import java.util.List;

public class UserService {
    public User getUserInfo(String email) {
        return new UserDao().getUser(email);
    }

    public List<User> showListUser() {
        return new UserDao().getAllUsers();
    }

    public void addMoney(String email, Double account) {
        new UserDao().addMoney(email, account);
    }

    public void changeStatusActive(User user, Integer status) {
        new UserDao().changeActiveStatus(user.getEmail(), status);
    }

    public List<User> viewAllUsersPerPage(int page, int recordsPerPage, List<User> list) {
        return new UserDao().viewAllByPage(page, recordsPerPage, list);
    }
}
