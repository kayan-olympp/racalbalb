package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
