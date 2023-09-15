package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.service.AccountService;
import com.bank.Bank.service.AccountTypeService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountTypeControllerTest {

    @InjectMocks
    AccountTypeController accountTypeController;

    @Mock
    AccountTypeService accountTypeService;

    @Test
    void create() {

        AccountTypeModel mockAccountTypeModel= new AccountTypeModel();
        when(accountTypeService.create(mockAccountTypeModel)).thenReturn(new ResponseEntity<>(mockAccountTypeModel, HttpStatus.OK));

        ResponseEntity<AccountTypeModel> response= accountTypeController.create(mockAccountTypeModel);

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockAccountTypeModel,response.getBody());

    }

    @Test
    void findAll() {
        List<AccountTypeModel> mockAccountTypeModelList=new ArrayList<>();

        when(accountTypeService.findAll()).thenReturn(ResponseEntity.ok(mockAccountTypeModelList));
        ResponseEntity<List<AccountTypeModel>> response=accountTypeController.findAll();

        assertNotNull(response);
       assertEquals(ResponseEntity.ok(mockAccountTypeModelList),response);
        verify(accountTypeService,times(1)).findAll();


    }

    @Test
    void findById() {

        Long id=1L;
        Optional<AccountTypeModel> mockAccountTypeModel= Optional.of(new AccountTypeModel());
        when(accountTypeService.findById(id)).thenReturn(ResponseEntity.ok(mockAccountTypeModel));

        ResponseEntity<Optional<AccountTypeModel>> response=accountTypeController.findById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockAccountTypeModel,response.getBody());

        verify(accountTypeService,times(1)).findById(id);
    }

    @Test
    void update() {
        // Arrange
        Long id = 1L; // Replace with a valid account ID
        AccountTypeModel mockAccountTypeModel = new AccountTypeModel(); // You can create a mock AccountModel here
        when(accountTypeService.update(eq(id), any(AccountTypeModel.class))).thenReturn(new ResponseEntity<>(mockAccountTypeModel, HttpStatus.OK));

        // Act
        ResponseEntity<AccountTypeModel> response = accountTypeController.update(id, mockAccountTypeModel);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccountTypeModel, response.getBody());

        // Verify that the accountService's updateAccount method was called with the provided account ID and AccountModel
        verify(accountTypeService, times(1)).update(eq(id), any(AccountTypeModel.class));


    }

    @Test
    void delete() {
        Long id = 1L;

        // Mocking the behavior of the accountTypeService.delete() method
        when(accountTypeService.delete(id)).thenReturn(ResponseEntity.ok("Deleted"));
        // Calling the delete method of the controller
        ResponseEntity<String> response = accountTypeController.delete(id);
        // Verify that the service method was called with the correct ID
        verify(accountTypeService).delete(id);
        // Verify the response status
        assertEquals("Deleted", response.getBody());
    }
}