package ua.epam.provider.user.info;

import java.time.LocalDate;
import java.util.List;

public class UserInfo {
    Integer id;
    String email;
    String title;
    List<String> serviceTitles;
    Double price;
    String status;
    LocalDate dateStart;

    public UserInfo() {
    }

    public UserInfo(Integer id, String email, String title, List<String> serviceTitles, Double price, String status, LocalDate dateStart) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.serviceTitles = serviceTitles;
        this.price = price;
        this.status = status;
        this.dateStart = dateStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getServiceTitles() {
        return serviceTitles;
    }

    public void setServiceTitles(List<String> serviceTitles) {
        this.serviceTitles = serviceTitles;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }
}
