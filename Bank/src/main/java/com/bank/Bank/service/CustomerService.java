package com.bank.Bank.service;

import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    //create

    public ResponseEntity<CustomerModel> createCustomer(CustomerModel customerModel) {
        try {
            customerRepository.save(customerModel);
            return new ResponseEntity<>(customerModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    //find all

    public ResponseEntity<List<CustomerModel>> findCustomers() {
        try {
            List<CustomerModel> allCustomers = customerRepository.findAll();
            return ResponseEntity.ok(allCustomers);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //find by id
    public ResponseEntity<Optional<CustomerModel>> findCustomersUsingId(Long customerId) {
        try {
            Optional<CustomerModel> customer = customerRepository.findById(customerId);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //update
    public ResponseEntity<CustomerModel> UpdateCustomer(Long customerId, CustomerModel customerModel) {
        try {
            CustomerModel oldCustomer = customerRepository.findById(customerId).orElseThrow();
            oldCustomer.setName(customerModel.getName());
            oldCustomer.setAddress(customerModel.getAddress());
            oldCustomer.setNic(customerModel.getNic());
            oldCustomer.setPhone(customerModel.getPhone());
            oldCustomer.setAccountModelList(customerModel.getAccountModelList());
            oldCustomer.setBankModel(customerModel.getBankModel());
            customerRepository.save(oldCustomer);

            return ResponseEntity.ok(oldCustomer);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //delete

    public ResponseEntity<String> DeleteCustomer(Long customerId) {
        try {
            customerRepository.deleteById(customerId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
