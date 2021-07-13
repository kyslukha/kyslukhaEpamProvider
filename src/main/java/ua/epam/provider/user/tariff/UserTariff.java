package ua.epam.provider.user.tariff;

import java.time.LocalDate;

public class UserTariff implements Comparable<UserTariff>{
    String title;
    LocalDate dateStart;
    LocalDate dateFinish;
    Integer id;



    public UserTariff() {
    }

    public UserTariff(String title, LocalDate dateStart, LocalDate dateFinish, Integer id) {
        this.title = title;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(UserTariff o) {
        return getDateStart().compareTo(o.getDateStart());
    }
}
