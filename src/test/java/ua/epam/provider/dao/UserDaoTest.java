package ua.epam.provider.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ua.epam.provider.entity.User;

class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    void createUser() {
        Integer number = userDao.getAllUsers().size();
        User user = new User("a", "a", "a", "1", 1);
        if (!userDao.isExistUser(user.getEmail())) {
            userDao.createUser(user);
            Assert.assertEquals(userDao.getAllUsers().size(), number + 1);
        }
        Assert.assertTrue(userDao.getAllUsers().size()==number);
    }

    @Test
    void updateUser() {


    }

    @Test
    void updateUserPassword() {


    }

    @Test
    void isExistUser() {

    }

    @Test
    void getAllUsers() {

    }


    @Test
    void deleteUser() {

    }
}