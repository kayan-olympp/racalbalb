package com.racalbalb.demo.domain;

import lombok.Data;
import org.springframework.hateoas.Identifiable;
import javax.persistence.*;

/**
 * This class reprensent a passenger
 */

@Data
@Entity
@Table(name="PASSENGER")
public class Passenger implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PASSENGER_ID")
    private Long id;

    @Column(name="PASSENGER_FIRSTNAME")
    private String firstname;

    @Column(name="PASSENGER_LASTNAME")
    private String lastname;

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
