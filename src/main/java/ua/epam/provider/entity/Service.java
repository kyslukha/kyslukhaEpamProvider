package ua.epam.provider.entity;


public class Service {

    private Integer id;

    private String title;


    public Service() {
    }

    public Service(String title) {
        this.title = title;
    }

    public Service(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
