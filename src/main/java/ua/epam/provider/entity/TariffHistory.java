package ua.epam.provider.entity;

import java.time.LocalDate;


public class TariffHistory {

    private Integer id;


    private LocalDate dateStart;


    private LocalDate dateFinish;


    private Integer status;


    private Integer tariffId;


    private Integer userId;

    public TariffHistory() {
    }

    public TariffHistory(Integer tariffId, Integer userId) {
        this.tariffId = tariffId;
        this.userId = userId;
    }

    public TariffHistory(LocalDate dateStart, Integer tariffId, Integer userId) {
        this.dateStart = dateStart;
        this.tariffId = tariffId;
        this.userId = userId;
    }

    public TariffHistory(Integer id, LocalDate dateStart, LocalDate dateFinish, Integer status, Integer userId, Integer tariffId) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
        this.tariffId = tariffId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setDateStart(LocalDate dateStart) {
        if (LocalDate.now().isAfter(dateStart)) {
            this.dateStart = LocalDate.now();
        } else {
            this.dateStart = dateStart;
        }
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish, LocalDate dateStart) {
        if (dateStart.plusDays(7L).isBefore(dateFinish)) {
            this.dateFinish = dateFinish;
        } else {
            this.dateFinish = dateStart.plusYears(1L);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(LocalDate dateStart, LocalDate dateFinish) {
        if (!(LocalDate.now().isBefore(dateStart)) && !(LocalDate.now().isAfter(dateFinish)) ) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }



    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TariffHistory{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                ", status=" + status +
                ", tariffId=" + tariffId +
                ", userId=" + userId +
                '}';
    }
}
