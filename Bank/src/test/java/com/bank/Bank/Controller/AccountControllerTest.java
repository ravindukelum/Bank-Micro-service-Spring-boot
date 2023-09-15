package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.service.AccountService;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @Test
    void createAccount() {

        AccountModel mockAccountModel = new AccountModel();
        when(accountService.createAccount(mockAccountModel)).thenReturn(new ResponseEntity<>(mockAccountModel,HttpStatus.OK));
        // Calling the createAccount method of the controller
        ResponseEntity<AccountModel> response = accountController.createAccount(mockAccountModel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(accountService).createAccount(mockAccountModel);


    }

    @Test
    void findAll() {
        // Arrange
        List<AccountModel> mockAccountList = new ArrayList<>(); // You can create a mock list of AccountModels here
        when(accountService.findAll()).thenReturn(ResponseEntity.ok(mockAccountList));

        ResponseEntity<List<AccountModel>> response= accountController.findAll();
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(mockAccountList),response);

        verify(accountService).findAll();
    }

    @Test
    void findById() {
        // Arrange
        Long accountId = 1L; // Replace with a valid account ID
        AccountModel mockAccountModel = new AccountModel(); // You can create a mock AccountModel here
        ResponseEntity<Optional<AccountModel>> mockResponseEntity =
                ResponseEntity.ok(Optional.of(mockAccountModel));

        when(accountService.findById(accountId)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<Optional<AccountModel>> response = accountController.findById(accountId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent()); // Check if the Optional contains a value
        assertEquals(mockAccountModel, response.getBody().get()); // Retrieve and compare the actual model
    }

    @Test
    void updateAccount() {
        Long accountId = 1L;
        AccountModel mockupdatedAccountModel = new AccountModel(/* Set up your test data */);
        when(accountService.updateAccount(eq(accountId),any(AccountModel.class))).thenReturn(new ResponseEntity<>(mockupdatedAccountModel,HttpStatus.OK));
        ResponseEntity<?> response=accountController.updateAccount(accountId,mockupdatedAccountModel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(accountService).updateAccount(accountId,mockupdatedAccountModel);

    }

    @Test
    void deleteAccount() {
        Long id=1L;
        AccountModel mockAccountModel=new AccountModel();
        when(accountService.deleteAccount(id)).thenReturn(ResponseEntity.ok("Deleted"));
        ResponseEntity<String> response= accountController.deleteAccount(id);
        assertEquals("Deleted",response.getBody());
        verify(accountService).deleteAccount(id);


    }
}