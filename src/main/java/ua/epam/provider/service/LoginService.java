package ua.epam.provider.service;


import ua.epam.provider.dao.UserDao;
import ua.epam.provider.encryptor.Encryptor;

public class LoginService {
    public boolean isUserValid(String email, String password, Integer statusUser) {
        return (new UserDao().isExistUser(email) &&
                new UserDao().getUser(email).getPassword().equals(Encryptor.encrypt(password, email))
                && (new UserDao().getUser(email).getStatusUser().equals(statusUser)));
    }
}