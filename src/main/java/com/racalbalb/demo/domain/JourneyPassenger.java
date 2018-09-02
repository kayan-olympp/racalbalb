package com.racalbalb.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class help to find journeys passengers
 */

@Data
@Entity
@Table(name="JOURNEY_PASSENGERS")
@IdClass(JourneyPassenger.class)
public class JourneyPassenger implements Serializable {

    @Id @Column(name="JOURNEY_JOURNEY_ID")
    public Long journeyId;

    @Id @Column(name="PASSENGERS_PASSENGER_ID")
    public Long passengerId;

    public JourneyPassenger(){ }

    public JourneyPassenger(Long p_journeyId, Long p_passengerId){
        this.journeyId = p_journeyId;
        this.passengerId = p_passengerId;
    }

}
