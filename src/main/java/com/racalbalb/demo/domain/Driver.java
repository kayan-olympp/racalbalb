package com.racalbalb.demo.domain;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

/**
 * This class reprensent a driver
 */

@Data
@Entity
@Table(name="DRIVER")
public class Driver implements Identifiable<Long>{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="DRIVER_ID")
    private Long id;

    @Column(name="DRIVER_FIRSTNAME")
    private String firstname;

    @Column(name="DRIVER_LASTNAME")
    private String lastname;

    public Driver() {}

    public Driver( Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
