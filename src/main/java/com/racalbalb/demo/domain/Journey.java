package com.racalbalb.demo.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * This class reprent a journey
 */

@Data
@Entity
@Table(name="JOURNEY")
public class Journey {

    @Id @Column(name="JOURNEY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="JOURNEY_FROM")
    private String from;

    @Column(name="JOURNEY_TO")
    private String to;

    @OneToOne
    @JoinColumn(name = "DRIVER_ID")
    private Driver driver;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="JOURNEY_PASSENGER",
            joinColumns=@JoinColumn(name="JOURNEY_ID", table ="JOURNEY_PASSENGER", referencedColumnName="JOURNEY_ID"),
            inverseJoinColumns=@JoinColumn(name="PASSENGER_ID", table ="JOURNEY_PASSENGER", referencedColumnName="PASSENGER_ID"))
    private List<Passenger> passengers;

    public Journey() {}

    public Journey( Long id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Long driverId() {
        return this.driver.getId();

    }

}
