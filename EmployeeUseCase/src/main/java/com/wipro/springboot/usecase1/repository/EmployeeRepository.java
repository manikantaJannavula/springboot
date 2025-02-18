package com.wipro.springboot.usecase1.repository;

import com.wipro.springboot.usecase1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for Employee entity, extends JpaRepository
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JpaRepository provides CRUD operations for the Employee entity
    // We do not need to explicitly define methods for basic CRUD as JpaRepository handles them
}
