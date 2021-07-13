package ua.epam.provider.service;

import ua.epam.provider.dao.TariffDao;
import ua.epam.provider.entity.Tariff;

import java.util.List;

public class TariffService {
   public List<Tariff> showListTariff(){
List<Tariff> tariffList = new TariffDao().showListTariffs();
return  tariffList;
   }

   public void deleteTariff( Tariff tariff){
      new TariffDao().deleteTariff(tariff);
   }

   public void addNewTariff( Tariff tariff){
      new TariffDao().createTariff( tariff);
   }



}
