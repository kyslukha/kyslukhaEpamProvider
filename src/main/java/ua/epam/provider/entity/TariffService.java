package ua.epam.provider.entity;

import javax.persistence.*;
import java.sql.Connection;

@Entity
@Table(name = "tariff_service")
public class TariffService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="tariff_id")
    private Integer tariffId;

    @ManyToOne
    @JoinColumn(name="service_id")
    private Integer serviceId;

    public TariffService() {
    }

    public TariffService(Integer id, Integer tariffId, Integer serviceId) {
        this.id = id;
        this.tariffId = tariffId;
        this.serviceId = serviceId;
    }

    public TariffService(int id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }


    public void deleteTariff(Connection connection, Tariff tariff) {
    }
}
