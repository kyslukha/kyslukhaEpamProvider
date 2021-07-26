package ua.epam.provider.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ua.epam.provider.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    void createUser() {
        int number = userDao.getAllUsers().size();
        String email = "email1@email.com";
        String phone = "1";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name1", "1", email, phone,0);
            userDao.createUser(user);
            user = userDao.getUser(email);
            Assert.assertEquals(userDao.getAllUsers().size(), number + 1);
            userDao.deleteUser(user);
        } else
            Assert.assertTrue(userDao.getAllUsers().size() == number);
    }

    @Test
    void updateUser() {
        String email = "Email@email.com";
        String phone = "111111";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("Name","1111", email, phone,0);
            userDao.createUser(user);
            user = userDao.getUser(email);
            String updateEmail = "EmailUpdate@email.com";
            String updatePhone = "222222";
            if (!userDao.isExistUser(updateEmail) && !userDao.isExistUserPhone(updatePhone)) {
                User newUser = new User("NewName", "2222", updateEmail, updatePhone,0);
                userDao.updateUser(user, newUser);
                Assert.assertTrue(userDao.isExistUser(updateEmail));
                newUser = userDao.getUser(updateEmail);
                userDao.deleteUser(newUser);
            } else {
                System.out.println("User for update with email " + updateEmail +
                        " or phone " + updatePhone + " is exists.");
                user = userDao.getUser(email);
                userDao.deleteUser(user);
            }
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void updateUserPassword() {
        String email = "email2@email.com";
        String phone = "2";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name2","2", email, phone,0);
            userDao.createUser(user);
            String newPassword = "1";
            String password = user.getPassword();
            userDao.updateUserPassword(user.getEmail(), newPassword);
            User newUser = userDao.getUser(email);
            if (newPassword.equals(password))
                Assert.assertTrue(newUser.getPassword().equals(user.getPassword()));
            else
                Assert.assertFalse(newUser.getPassword().equals(user.getPassword()));
            userDao.deleteUser(newUser);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }


    @Test
    void deleteUser() {
        String email = "email3@email.com";
        String phone = "3";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name3", "3", email, phone,0);
            userDao.createUser(user);
            int number = userDao.getAllUsers().size();
            user = userDao.getUser(email);
            userDao.deleteUser(user);
            Assert.assertEquals(userDao.getAllUsers().size(), number - 1);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");

    }

    @Test
    void isExistUser() {
        String email = "email4@email.com";
        String phone = "4";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name4", "4", email, phone,0);
            userDao.createUser(user);
            Assert.assertTrue(userDao.isExistUser(email));
            user = userDao.getUser(email);
            userDao.deleteUser(user);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        for (User user : userList
        ) {
            Assert.assertTrue(userDao.isExistUser(user.getEmail()));
            Assert.assertTrue(userDao.isExistUserPhone(user.getPhone()));
        }
    }

    @Test
    void getUser() {
        String email = "email5@email.com";
        String phone = "5";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name5", "5", email, phone,0);
            userDao.createUser(user);
            Assert.assertTrue(userDao.isExistUser(userDao.getUser(email).getEmail()));
            Assert.assertTrue(userDao.isExistUserPhone(userDao.getUser(email).getPhone()));
            user = userDao.getUser(email);
            userDao.deleteUser(user);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void changeActiveStatus() {
        String email = "email6@email.com";
        String phone = "6";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name6", "6", email, phone,0);
            userDao.createUser(user);
            Random random = new Random();
            Integer status = random.nextInt(1);
            userDao.changeActiveStatus(user.getEmail(), status);
            User newUser = userDao.getUser(user.getEmail());
            Assert.assertTrue(newUser.getStatusActive().equals(status));
            userDao.deleteUser(newUser);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void addMoney() {
        String email = "email7@email.com";
        String phone = "7";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name7", "7", email, phone,0);
            userDao.createUser(user);
            user = userDao.getUser(email);
            Double account = user.getAccount();
            Random random = new Random();
            Double newAccount = random.nextDouble();
            BigDecimal result = new BigDecimal(newAccount);
            result = result.setScale(2, RoundingMode.DOWN);
            newAccount = result.doubleValue();
            if (newAccount > 0) {
                userDao.addMoney(user.getEmail(), newAccount + account);
                User newUser = userDao.getUser(user.getEmail());
                Assert.assertTrue(newUser.getAccount().equals(newAccount + account));
            } else
                Assert.assertTrue(user.getAccount().equals(account));
            userDao.deleteUser(user);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void findUserById() {
        String email = "email8@email.com";
        String phone = "8";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name8", "8", email, phone,0);
            userDao.createUser(user);
            user = userDao.getUser(email);
            User newUser = userDao.findUserById(user.getId());
            Assert.assertTrue(newUser.getEmail().equals(user.getEmail()));
            Assert.assertTrue(newUser.getPhone().equals(user.getPhone()));
            userDao.deleteUser(user);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }

    @Test
    void isExistUserPhone() {
        String email = "email9@email.com";
        String phone = "9";
        if (!userDao.isExistUser(email) && !userDao.isExistUserPhone(phone)) {
            User user = new User("name9", "9", email, phone,0);
            userDao.createUser(user);
            Assert.assertTrue(userDao.isExistUserPhone(phone));
            user = userDao.getUser(email);
            userDao.deleteUser(user);
        } else System.out.println("User for update with email " + email +
                " or phone " + phone + " is exists.");
    }
}