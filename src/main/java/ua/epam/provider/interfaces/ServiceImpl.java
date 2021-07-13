package ua.epam.provider.interfaces;

import ua.epam.provider.entity.Service;

import java.util.List;

public interface ServiceImpl {

    void createService( String title);

    void deleteService( Service service);

    void updateService( Service oldService, Service service);

    List<Service> showListServices();

    boolean isExistService( String title);

    Service getService( String title);

    List<String> showListServicesTitles();

    Service findServiceById( Integer id);
}
