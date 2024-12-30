package com.maybank.assessment.repository;

import com.maybank.assessment.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
