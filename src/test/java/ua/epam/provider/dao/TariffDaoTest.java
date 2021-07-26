package ua.epam.provider.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ua.epam.provider.entity.Tariff;

import java.util.List;

class TariffDaoTest {
    TariffDao tariffDao = new TariffDao();

    @Test
    void createTariff() {
        int number = tariffDao.showListTariffs().size();
        String string = "new title1";
        Double price = 2.3;
        Tariff tariff = new Tariff(price, string);
        if (!tariffDao.isExistTariff(string)) {
            tariffDao.createTariff(tariff);
            tariff = tariffDao.getTariff(tariff.getTitle());
            Assert.assertEquals(tariffDao.showListTariffs().size(), number + 1);
            tariffDao.deleteTariff(tariff);
        } else
            Assert.assertTrue(tariffDao.showListTariffs().size() == number);
    }

    @Test
    void updateTariff() {
        Tariff tariff = new Tariff();
        String string = "New title";
        Double price = 2.3;
        if (!tariffDao.isExistTariff(string)) {
            tariff = new Tariff(price, string);
            tariffDao.createTariff(tariff);
            tariff = tariffDao.getTariff(string);
            String stringUpdate = "Update title";
            Double priceUpdate = 3.2;
            if (!tariffDao.isExistTariff(stringUpdate)) {
                Tariff newTariff = new Tariff(priceUpdate, stringUpdate);
                tariffDao.updateTariff(tariff, newTariff);
                Assert.assertTrue(tariffDao.isExistTariff(stringUpdate));
                newTariff = tariffDao.getTariff(stringUpdate);
                tariffDao.deleteTariff(newTariff);
            } else {
                System.out.println("Tariff for update with title " + stringUpdate + " is exists.");
                tariff = tariffDao.getTariff(string);
                tariffDao.deleteTariff(tariff);
            }
        } else System.out.println("Tariff with title " + string + " is exists.");
    }

    @Test
    void deleteTariff() {
        String string = "new title2";
        Double price = 2.3;
        Tariff tariff = new Tariff(price, string);
        if (!tariffDao.isExistTariff(tariff.getTitle())) {
            tariffDao.createTariff(tariff);
            int number = tariffDao.showListTariffs().size();
            tariff = tariffDao.getTariff(tariff.getTitle());
            tariffDao.deleteTariff(tariff);
            Assert.assertEquals(tariffDao.showListTariffs().size(), number - 1);
        } else System.out.println(" Tariff with title " + string + " is exists.");
    }

    @Test
    void getTariff() {
        String string = "new title3";
        Double price = 2.3;
        if (!tariffDao.isExistTariff(string)) {
            Tariff tariff = new Tariff(price, string);
            tariffDao.createTariff(tariff);
            Assert.assertTrue(tariffDao.isExistTariff(tariffDao.getTariff(string).getTitle()));
            tariff = tariffDao.getTariff(string);
            tariffDao.deleteTariff(tariff);
        } else System.out.println("Tariff with title " + string + " is exists.");
    }

    @Test
    void isExistTariff() {
        List<Tariff> tariffs = tariffDao.showListTariffs();
        for (Tariff tariff : tariffs
        ) {
            Assert.assertTrue(tariffDao.isExistTariff(tariff.getTitle()));
        }
    }


    @Test
    void findTariffById() {
        String string = "new title4";
        Double price = 2.3;
        if (!tariffDao.isExistTariff(string)) {
            Tariff tariff = new Tariff(price, string);
            tariffDao.createTariff(tariff);
            tariff = tariffDao.getTariff(string);
            Tariff newTariff = tariffDao.findTariffById(tariff.getId());
            Assert.assertTrue(newTariff.getTitle().equals(tariff.getTitle()));
            tariffDao.deleteTariff(tariff);
        } else System.out.println("Tariff with title " + string + " is exists.");
    }


    @Test
    void showListTariffs() {
        List<Tariff> tariffList = tariffDao.showListTariffs();
        for (Tariff tariff : tariffList
        ) {
            Assert.assertTrue(tariffDao.isExistTariff(tariff.getTitle()));
        }
    }

    @Test
    void showListTariffTitles() {
        String string = "new title5";
        Double price = 2.3;
        if (!tariffDao.isExistTariff(string)) {
            Tariff tariff = new Tariff(price, string);
            tariffDao.createTariff(tariff);
            List<String> tariffs = tariffDao.showListTariffTitles();
            int account = 0;
            for (String title : tariffs
            ) {
                if (title.equals(string))
                    account++;
            }
            Assert.assertTrue(account == 1);
            tariff = tariffDao.getTariff(string);
            tariffDao.deleteTariff(tariff);
        }else  System.out.println("Tariff with title " + string + " is exists.");
    }
}