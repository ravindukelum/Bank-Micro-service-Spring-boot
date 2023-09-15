package com.bank.Bank.Controller;

import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;
    @Test
    void createCustomer() {
        CustomerModel mockCustomermodel= new CustomerModel();
        Mockito.when(customerService.createCustomer(mockCustomermodel)).thenReturn(ResponseEntity.ok(mockCustomermodel));
        ResponseEntity<CustomerModel> response= customerController.createCustomer(mockCustomermodel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockCustomermodel,response.getBody());
    }

    @Test
    void findCustomers() {
        List<CustomerModel> mockCustomerModel=new ArrayList<>();
        Mockito.when(customerService.findCustomers()).thenReturn(ResponseEntity.ok(mockCustomerModel));
        ResponseEntity<List<CustomerModel>> response= customerController.findCustomers();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockCustomerModel,response.getBody());
    }

    @Test
    void findCustomersUsingId() {
        Long id=1L;
        Optional<CustomerModel> mockCustomerModel= Optional.of(new CustomerModel());
        Mockito.when(customerService.findCustomersUsingId(id)).thenReturn(ResponseEntity.ok(mockCustomerModel));
        ResponseEntity<Optional<CustomerModel>> response=customerController.findCustomersUsingId(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockCustomerModel,response.getBody());
    }

    @Test
    void updateCustomer() {
        Long id=1L;
        CustomerModel mockCustomerModel=new CustomerModel();
        Mockito.when(customerService.UpdateCustomer(id,mockCustomerModel)).thenReturn(ResponseEntity.ok(mockCustomerModel));
        ResponseEntity<CustomerModel> response=customerController.UpdateCustomer(id,mockCustomerModel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockCustomerModel,response.getBody());
    }

    @Test
    void deleteCustomer() {
        Long id=1L;
        Mockito.when(customerService.DeleteCustomer(id)).thenReturn(ResponseEntity.ok("Deleted"));
        ResponseEntity<String> response=customerController.DeleteCustomer(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Deleted",response.getBody());

    }
}