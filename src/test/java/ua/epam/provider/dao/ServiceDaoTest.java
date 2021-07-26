package ua.epam.provider.dao;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ua.epam.provider.entity.Service;

import java.util.List;

class ServiceDaoTest {
    ServiceDao serviceDao = new ServiceDao();


    @Test
    void createService() {

        int number = serviceDao.showListServices().size();
        String string = "new title1";
        Service service = new Service(string);
        if (!serviceDao.isExistService(string)) {
            serviceDao.createService(service.getTitle());
            service = serviceDao.getService(service.getTitle());
            Assert.assertEquals(serviceDao.showListServices().size(), number + 1);
            serviceDao.deleteService(service);
        } else
            Assert.assertTrue(serviceDao.showListServices().size() == number);
    }


    @Test
    void deleteService() {
        String string = "new title2";
        Service service = new Service(string);
        if (!serviceDao.isExistService(service.getTitle())) {
            serviceDao.createService(service.getTitle());
            int number = serviceDao.showListServices().size();
            service = serviceDao.getService(service.getTitle());
            serviceDao.deleteService(service);
            Assert.assertEquals(serviceDao.showListServices().size(), number - 1);
        } else System.out.println(" Service with title " + string + " is exists.");
    }

    @Test
    void updateService() {
        Service service = new Service();
        String string = "New title";
        if (!serviceDao.isExistService(string)) {
            service = new Service(string);
            serviceDao.createService(service.getTitle());
            service = serviceDao.getService(string);
            String stringUpdate = "Update title";
            if (!serviceDao.isExistService(stringUpdate)) {
                Service newService = new Service(stringUpdate);
                serviceDao.updateService(service, newService);
                Assert.assertTrue(serviceDao.isExistService(stringUpdate));
                newService = serviceDao.getService(stringUpdate);
                serviceDao.deleteService(newService);
            } else {
                System.out.println("Service for update with title " + stringUpdate + " is exists.");
                service = serviceDao.getService(string);
                serviceDao.deleteService(service);
            }
        } else System.out.println("Service with title " + string + " is exists.");
    }

    @Test
    void showListServices() {
        List<Service> serviceList = serviceDao.showListServices();
        for (Service service : serviceList
        ) {
            Assert.assertTrue(serviceDao.isExistService(service.getTitle()));
        }
    }

    @Test
    void isExistService() {
        String string = "new title3";
        if (!serviceDao.isExistService(string)) {
            Service service = new Service(string);
            serviceDao.createService(service.getTitle());
            Assert.assertTrue(serviceDao.isExistService(string));
            service = serviceDao.getService(string);
            serviceDao.deleteService(service);
        } else System.out.println("Service with title " + string + " is exists.");
    }

    @Test
    void getService() {
        String string = "new title4";
        if (!serviceDao.isExistService(string)) {
            Service service = new Service(string);
            serviceDao.createService(service.getTitle());
            Assert.assertTrue(serviceDao.isExistService(serviceDao.getService(string).getTitle()));
            service = serviceDao.getService(string);
            serviceDao.deleteService(service);
        } else System.out.println("Service with title " + string + " is exists.");

    }

    @Test
    void findServiceById() {
        String string = "new title5";
        if (!serviceDao.isExistService(string)) {
            Service service = new Service(string);
            serviceDao.createService(service.getTitle());
            service = serviceDao.getService(string);
            Service newService = serviceDao.findServiceById(service.getId());
            Assert.assertTrue(newService.getTitle().equals(service.getTitle()));
            serviceDao.deleteService(service);
        } else System.out.println("Service with title " + string + " is exists.");
    }

}