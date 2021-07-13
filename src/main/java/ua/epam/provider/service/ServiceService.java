package ua.epam.provider.service;

import ua.epam.provider.dao.ServiceDao;
import ua.epam.provider.entity.Service;

import java.util.List;

public class ServiceService {
    public List<Service> showListServices(){
        List<Service> serviceList = new ServiceDao().showListServices();
        return serviceList;
    }



    public void addNewService( String title) {
        new ServiceDao().createService( title);
    }


    public void deleteService( Service service) {
        new ServiceDao().deleteService( service);
    }
}
