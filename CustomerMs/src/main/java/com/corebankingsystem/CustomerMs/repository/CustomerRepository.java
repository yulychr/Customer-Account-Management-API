package com.corebankingsystem.CustomerMs.repository;

import com.corebankingsystem.CustomerMs.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByDni(String dni);
}

