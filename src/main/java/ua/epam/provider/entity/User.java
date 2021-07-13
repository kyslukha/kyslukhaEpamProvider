package ua.epam.provider.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "account", columnDefinition = "0.0")
    private Double account;

    @Column(name = "status_active", columnDefinition = "1")
    private Integer statusActive;

    @Column(name = "status_user")
    private Integer statusUser;




    public User() {
    }

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String name, String password, String email, String phone, Integer statusUser) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.statusUser = statusUser;
    }

    public User(Integer id, String name, String password, String email, String phone, Double account, Integer statusActive, Integer statusUser) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.account = account;
        this.statusActive = statusActive;
        this.statusUser = statusUser;
    }



    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, Integer statusUser) {
        this.email = email;
        this.password = password;
        this.statusUser = statusUser;
    }

    public User(Integer id, String email, String password, Double account) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }



    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Integer getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(Integer statusActive) {
        this.statusActive = statusActive;
    }

    public Integer getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(Integer statusUser) {
        this.statusUser = statusUser;
    }

}
