package ua.epam.provider.interfaces;

import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;
import ua.epam.provider.entity.TariffService;

import java.sql.SQLException;
import java.util.List;

public interface TariffServiceImpl {
    List<Service> findAllServicesForTariff( Tariff tariff) throws SQLException;

    List<Tariff> findAllTariffsForService(Service service);

    List<TariffService> showListAll();

    void createTariffServices(Tariff tariff, List<Service> serviceList);
}
