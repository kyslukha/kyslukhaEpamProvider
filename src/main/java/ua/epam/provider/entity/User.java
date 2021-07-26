package ua.epam.provider.entity;


public class User {

    private Integer id;


    private String name;


    private String password;


    private String email;


    private String phone;


    private Double account;


    private Integer statusActive;


    private Integer statusUser;


//    private static final Logger log = Logger.getLogger(String.valueOf(User.class.getClass()));

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
