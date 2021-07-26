package ua.epam.provider.entity;


public class Tariff {

    private Integer id;

    private Double priceByDay;

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
