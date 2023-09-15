package com.bank.Bank.service;

import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.CustomerRepository;
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
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Test
    void createCustomerSuccess() {
        CustomerModel customerModelMock=new CustomerModel();
        Mockito.when(customerRepository.save(customerModelMock)).thenReturn(customerModelMock);
        ResponseEntity<CustomerModel> response=customerService.createCustomer(customerModelMock);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customerModelMock,response.getBody());
    }
    @Test
    void createCustomerFail() {
        CustomerModel customerModelMock=new CustomerModel();
        Mockito.when(customerRepository.save(customerModelMock)).thenThrow(new RuntimeException());
        ResponseEntity<CustomerModel> response=customerService.createCustomer(customerModelMock);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findCustomersSuccess() {
        List<CustomerModel> customerModelsMock=new ArrayList<>();
        Mockito.when(customerRepository.findAll()).thenReturn(customerModelsMock);
        ResponseEntity<List<CustomerModel>> response= customerService.findCustomers();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customerModelsMock,response.getBody());
    }
    @Test
    void findCustomersFail() {
        List<CustomerModel> customerModelsMock=new ArrayList<>();
        Mockito.when(customerRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<List<CustomerModel>> response= customerService.findCustomers();
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findCustomersUsingIdSuccess() {
        Long id=1L;
        Optional<CustomerModel> customerModelMock = Optional.of(new CustomerModel());
        Mockito.when(customerRepository.findById(id)).thenReturn(customerModelMock);
        ResponseEntity<Optional<CustomerModel>> response=customerService.findCustomersUsingId(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customerModelMock,response.getBody());
    }
    @Test
    void findCustomersUsingIdFail() {
        Long id=1L;
        CustomerModel customerModelMock =new CustomerModel();
        Mockito.when(customerRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<Optional<CustomerModel>> response=customerService.findCustomersUsingId(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateCustomerSuccess() {
        Long id=1L;
        CustomerModel customerModelMock =new CustomerModel();
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customerModelMock));
        ResponseEntity<CustomerModel> resonse=customerService.UpdateCustomer(id,customerModelMock);
        assertNotNull(resonse);
        assertEquals(HttpStatus.OK,resonse.getStatusCode());
        assertEquals(customerModelMock,resonse.getBody());
    }
    @Test
    void updateCustomerFail() {
        Long id=1L;
        CustomerModel customerModelMock =new CustomerModel();
        Mockito.when(customerRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<CustomerModel> resonse=customerService.UpdateCustomer(id,customerModelMock);
        assertNotNull(resonse);
        assertEquals(HttpStatus.NOT_FOUND,resonse.getStatusCode());
        assertNull(resonse.getBody());
    }

    @Test
    void deleteCustomerSuccess() {
        Long id=1L;
        Mockito.doNothing().when(customerRepository).deleteById(id);
        ResponseEntity<String> response = customerService.DeleteCustomer(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }
    @Test
    void deleteCustomerFail() {
        Long id=1L;
        Mockito.doThrow(new RuntimeException()).when(customerRepository).deleteById(id);
        ResponseEntity<String> response = customerService.DeleteCustomer(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}