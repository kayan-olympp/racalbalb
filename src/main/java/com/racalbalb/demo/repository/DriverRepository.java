package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * CRUD Operations
 */

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
}
