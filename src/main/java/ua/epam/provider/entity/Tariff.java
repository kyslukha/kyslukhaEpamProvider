package ua.epam.provider.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price_by_day", nullable = false)
    private Double priceByDay;

    @Column(name = "title", nullable = false, unique = true)
    private String title;


    public Tariff() {
    }

    public Tariff(Double priceByDay, String title) {
        this.priceByDay = priceByDay;
        this.title = title;
    }


    public Tariff(Integer id, Double priceByDay, String title) {
        this.id = id;
        this.priceByDay = priceByDay;
        this.title = title;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceByDay() {
        return priceByDay;
    }

    public void setPriceByDay(Double priceByDay) {
        this.priceByDay = priceByDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
