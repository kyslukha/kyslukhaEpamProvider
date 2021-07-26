package ua.epam.provider.timer;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.dao.TariffHistoryDao;
import ua.epam.provider.dao.UserDao;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;

import java.util.List;

public class Check {
    private UserDao userDao = new UserDao();
    private TariffHistoryDao tariffHistoryDao = new TariffHistoryDao();
    private TariffDao tariffDao = new TariffDao();

    public void checkAllUsers() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            if (user.getStatusActive().equals(1)) {
                Double sum = 0.0;
                List<TariffHistory> tariffHistories = tariffHistoryDao.showListActiveAndFutureUsersTariffs(user);
                for (TariffHistory tariff : tariffHistories) {
                    if (tariffHistoryDao.validationUserTariff(tariff)) {
                        if (tariff.getStatus().equals(0)) {
                            tariff.setStatus(1);
                            tariffHistoryDao.checkStatusTariff(tariff);
                        }
                        sum = sum + tariffDao.findTariffById(tariff.getTariffId()).getPriceByDay();
                    } else if (tariff.getStatus().equals(1)) {
                        tariff.setStatus(0);
                        tariffHistoryDao.checkStatusTariff(tariff);
                    }
                }
                if (user.getAccount() >= sum) {
                    userDao.addMoney(user.getEmail(), user.getAccount() - sum);
                } else {
                    userDao.changeActiveStatus(user.getEmail(), 0);
                    tariffHistoryDao.inactiveStatusTariff(user);
                }
            }
        }
    }

    public void checkInactiveUser(User user){
       if (user.getStatusActive().equals(0)){
           Double sum = 0.0;
           List<TariffHistory> tariffHistories = tariffHistoryDao.showListUsersTariffHistories(user);
           for (TariffHistory tariff : tariffHistories) {
               if (tariffHistoryDao.validationUserTariff(tariff)) {
                   sum = sum + tariffDao.findTariffById(tariff.getTariffId()).getPriceByDay();
               }
           } if (user.getAccount() >= sum) {
               userDao.addMoney(user.getEmail(), user.getAccount() - sum);
               user.setAccount(user.getAccount()-sum);
               userDao.changeActiveStatus(user.getEmail(), 1);
               tariffHistoryDao.activeStatusTariff(user);
           }
       }
    }
    public void checkActiveUser(User user){
        Double sum = 0.0;
        List<TariffHistory> tariffHistories = tariffHistoryDao.showListUsersTariffHistories(user);
        for (TariffHistory tariff : tariffHistories) {
            if (tariffHistoryDao.validationUserTariff(tariff)) {
                sum = sum + tariffDao.findTariffById(tariff.getTariffId()).getPriceByDay();
            }
        }if (user.getAccount() >= sum){
            userDao.addMoney(user.getEmail(), user.getAccount() - sum);
            user.setAccount(user.getAccount() - sum);
            tariffHistoryDao.activeStatusTariff(user);
        }else{
            userDao.changeActiveStatus(user.getEmail(), 0);
        }

    }
}
