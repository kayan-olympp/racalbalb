package com.racalbalb.demo.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

/**
 * This class reprent a journey
 */

@Data
@Entity
@Table(name="JOURNEY")
public class Journey {

    @Id @Column(name="JOURNEY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(name="JOURNEY_FROM")
    public String from;

    @Column(name="JOURNEY_TO")
    public String to;

    @OneToOne
    @JoinColumn(name = "DRIVER_ID")
    public Driver driver;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "JOURNEY_PASSENGERS",
            joinColumns = { @JoinColumn(name = "PASSENGERS_PASSENGER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "JOURNEY_JOURNEY_ID") }
    )
    public Set<Passenger> passengers;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
