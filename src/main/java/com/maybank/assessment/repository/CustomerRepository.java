package com.maybank.assessment.repository;

import com.maybank.assessment.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByFirstName(String name);

}
