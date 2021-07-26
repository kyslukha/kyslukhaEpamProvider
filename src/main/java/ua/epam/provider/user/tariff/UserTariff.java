package ua.epam.provider.user.tariff;

import java.time.LocalDate;

public class UserTariff implements Comparable<UserTariff>{
    String title;
    LocalDate dateStart;
    LocalDate dateFinish;
    Integer id;
    String status;
    public UserTariff() {
    }
    public UserTariff(String title, LocalDate dateStart, LocalDate dateFinish, Integer id, String status) {
        this.title = title;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.id = id;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int compareTo(UserTariff o) {
        return getDateStart().compareTo(o.getDateStart());
    }
}
