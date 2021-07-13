package ua.epam.provider.interfaces;

import ua.epam.provider.entity.Tariff;

import java.sql.SQLException;
import java.util.List;

public interface TariffImpl {

    void createTariff(Tariff tariff) throws SQLException;

    void updateTariff( Tariff oldTariff, Tariff tariff) throws SQLException;

    void deleteTariff( Tariff tariff) throws SQLException;

    Tariff getTariff( String title);

    boolean isExistTariff( String title);

    List<Tariff> showListTariffs();

    List<String> showListTariffTitles();

    Tariff findTariffById( Integer id);

    List<Tariff> listTariffs( String sqlString);


}
