package com.maybank.assessment.api;


import com.maybank.assessment.api.generated.CustomerApi;
import com.maybank.assessment.model.generated.Customer;
import com.maybank.assessment.model.generated.CustomerRequestBody;
import com.maybank.assessment.repository.CustomerRepository;
import com.maybank.assessment.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController implements CustomerApi{

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Void> customersIdDelete(Integer id) {
        return customerService.deleteCustomer(id);
    }

    @Override
    public ResponseEntity<Customer> customersIdGet(Integer id) {
        return customerService.getCustomerById(id);
    }

    @Override
    public ResponseEntity<Customer> customersIdPut(Integer id, CustomerRequestBody customerRequestBody) {
        return customerService.updateCustomer(id, customerRequestBody);
    }

    @Override
    public ResponseEntity<Customer> customersPost(CustomerRequestBody customerRequestBody) {
        return customerService.createCustomer(customerRequestBody);


    }
}