package ua.epam.provider.service;

import ua.epam.provider.dao.TariffServiceDao;
import ua.epam.provider.entity.Service;
import ua.epam.provider.entity.Tariff;

import java.util.List;

public class TariffServiceService {
    public List<Service> showListServicesOfTariff(Tariff tariff) {
        List<Service> serviceList = new TariffServiceDao().findAllServicesForTariff(tariff);
        return serviceList;
    }

    public void createTariffServices(Tariff tariff, List<Service> serviceList) {
        new TariffServiceDao().createTariffServices(tariff, serviceList);
    }
}
