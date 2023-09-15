package com.bank.Bank.Controller;

import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //create
    @PostMapping("/create")
    public ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerModel customerModel){
       return customerService.createCustomer(customerModel);
    }
    //find all
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerModel>> findCustomers(){
        return customerService.findCustomers();
    }

    //find by id

    @GetMapping("/{customerId}")
    public ResponseEntity<Optional<CustomerModel>> findCustomersUsingId(@Positive(message = "Id must be positive") @PathVariable Long customerId){
        return customerService.findCustomersUsingId(customerId);
    }

    //update
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerModel> UpdateCustomer(@Positive(message = "Id must be positive") @PathVariable Long customerId,@Valid @RequestBody CustomerModel customerModel){
        return customerService.UpdateCustomer(customerId,customerModel);
    }

    //delete
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> DeleteCustomer(@Positive(message = "Id must be positive") @PathVariable Long customerId){
       return customerService.DeleteCustomer(customerId);
    }
}

