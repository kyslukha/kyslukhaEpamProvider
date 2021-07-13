package ua.epam.provider.service;

import ua.epam.provider.dao.TariffHistoryDao;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;

import java.time.LocalDate;
import java.util.List;

public class TariffHistoryService {
    public List<TariffHistory> showUserTariffHistory( User user){
        List<TariffHistory> list = new TariffHistoryDao().showListUsersTariffHistories( user);
        return list;
    }
    public List<TariffHistory> showActiveAndFutureUserTariff(User user){
        List<TariffHistory> list = new TariffHistoryDao().showListActiveAndFutureUsersTariffs(user);
        return list;
    }

    public void createUserTariff( TariffHistory tariffHistory){
        new TariffHistoryDao().createUserTariff( tariffHistory);
    }

    public TariffHistory findById( Integer id){
       return new TariffHistoryDao().findById( id);
    }

    public  void updateUserTariff( TariffHistory tariffHistory, LocalDate dateFinish){
        new TariffHistoryDao().updateUserTariff( tariffHistory, dateFinish);
    }
    public boolean validation( User user, TariffHistory userTariff){
        boolean check = true;
        List<TariffHistory> listUserTariff = showActiveAndFutureUserTariff( user);
        for (TariffHistory checkTariff : listUserTariff) {
            if (checkTariff.getTariffId().equals(userTariff.getTariffId()) &&
                    ( checkTariff.getDateFinish().plusDays(7L).isAfter(userTariff.getDateStart()))) {
                check = false;
            }
        }
        return check;
    }

    public void deleteUserTariff( TariffHistory tariffHistory){
        new TariffHistoryDao().deleteUserTariff( tariffHistory);
    }
}
