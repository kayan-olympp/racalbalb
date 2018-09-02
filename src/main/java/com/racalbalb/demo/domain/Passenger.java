package com.racalbalb.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;

/**
 * This class reprensent a passenger
 */

@Data
@Entity
@Table(name="PASSENGER")
public class Passenger implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PASSENGER_ID")
    private Long id;

    @Column(name="PASSENGER_FIRSTNAME")
    private String firstname;

    @Column(name="PASSENGER_LASTNAME")
    private String lastname;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @ManyToMany(mappedBy="passengers")
    private List<Journey> journeys;
    public Passenger() {}

    public Passenger( Long id, String firstname, String lastname) {
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
