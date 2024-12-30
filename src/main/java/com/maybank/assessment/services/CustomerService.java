package com.maybank.assessment.services;

import com.maybank.assessment.entities.customer.Customer;
import com.maybank.assessment.entities.customer.mapper.CustomerMapper;
import com.maybank.assessment.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseEntity<com.maybank.assessment.model.generated.Customer> getCustomerById (Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(Long.valueOf(id));

        if (customerOptional.isPresent()) {
            com.maybank.assessment.model.generated.Customer generatedCustomer =
                    customerMapper.toGeneratedModel(customerOptional.get());
            return ResponseEntity.ok(generatedCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteCustomer (Integer id) {
        try {
            customerRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    public ResponseEntity<com.maybank.assessment.model.generated.Customer> createCustomer (
            com.maybank.assessment.model.generated.CustomerRequestBody custBody
    ) {
        try {
            com.maybank.assessment.entities.customer.Customer customerEntity =
                    customerMapper.fromGeneratedRequestBodytoEntity(custBody);

            com.maybank.assessment.entities.customer.Customer savedCustomer =
                    customerRepository.save(customerEntity);

            com.maybank.assessment.model.generated.Customer responseCustomer =
                    customerMapper.toGeneratedModel(savedCustomer);

            logger.info("Customer saved successfully: {}", responseCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseCustomer);

        } catch (Exception e) {

            logger.error("Customer save unsuccessful: {}", custBody, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<com.maybank.assessment.model.generated.Customer> updateCustomer(
            Integer id,
            com.maybank.assessment.model.generated.CustomerRequestBody custBody
    ) {
        try {
            com.maybank.assessment.entities.customer.Customer existingCustomer =
                    customerRepository.findById(Long.valueOf(id)).orElse(null);

            if (existingCustomer == null) {
                logger.error("Customer with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }


            com.maybank.assessment.entities.customer.Customer updatedCustomer =
                    customerMapper.fromGeneratedRequestBodytoEntity(custBody);

            updatedCustomer.setOid(existingCustomer.getOid());

            com.maybank.assessment.entities.customer.Customer savedCustomer =
                    customerRepository.save(updatedCustomer);

            com.maybank.assessment.model.generated.Customer responseCustomer =
                    customerMapper.toGeneratedModel(savedCustomer);

            // Log the successful update and return the response
            logger.info("Customer updated successfully: {}", responseCustomer);
            return ResponseEntity.status(HttpStatus.OK).body(responseCustomer);

        } catch (Exception e) {
            // Log the error and return a BAD REQUEST (400) with an empty body
            logger.error("Customer update unsuccessful: {}", custBody, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
