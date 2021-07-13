package ua.epam.provider.interfaces;

import ua.epam.provider.entity.User;

import java.util.List;

public interface UserImpl {
    void createUser( User user);

    void updateUser( User oldUser,User user);

    void updateUserPassword( String email, String password);

    boolean isExistUser( String email);

    List<User> getAllUsers();

    User getUser( String email);

    void deleteUser( User user);

    void changeActiveStatus( String email, Integer status);

    void addMoney( String email, Double sum);

    User findUserById( Integer id);
}
