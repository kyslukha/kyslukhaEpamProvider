package ua.epam.provider.interfaces;

import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffHistory;
import ua.epam.provider.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TariffHistoryImpl {


    void createUserTariff(TariffHistory tariffHistory);

    void updateUserTariff(TariffHistory tariffHistory, LocalDate dateFinish);

    void inactiveStatusTariff(User user);

    boolean isExistUserTariff(User user, Tariff tariff);

    TariffHistory getUserTariff(Tariff tariff, User user);

    List<TariffHistory> showListAllTariffs();

    List<TariffHistory> showListAllActiveTariffs();

    List<TariffHistory> showListUsersTariffHistories(User user);

    List<TariffHistory> showListActiveAndFutureUsersTariffs(User user);

    void activeStatusTariff(User user);

    TariffHistory findById(Integer id);

    void deleteUserTariff(TariffHistory tariffHistory);

    boolean validationUserTariff(TariffHistory tariffHistory);

    void checkStatusTariff(TariffHistory tariffHistory);
}
