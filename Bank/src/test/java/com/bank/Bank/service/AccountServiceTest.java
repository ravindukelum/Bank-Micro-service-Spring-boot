package com.bank.Bank.service;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    @InjectMocks
    AccountService accountService;
    @Mock
    AccountRepository accountRepository;

    @Test
    void createAccountSuccess() {
        AccountModel mockAccountModel = new AccountModel();
        Mockito.when(accountRepository.save(mockAccountModel)).thenReturn(mockAccountModel);
        ResponseEntity<AccountModel> response = accountService.createAccount(mockAccountModel);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAccountModel, response.getBody());
    }

    @Test
    void createAccountFail() {
        AccountModel mockAccountModel = new AccountModel();
        // Simulate the repository save failure
        Mockito.when(accountRepository.save(mockAccountModel)).thenThrow(new RuntimeException("Failed to save"));
        ResponseEntity<AccountModel> response = accountService.createAccount(mockAccountModel);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody()); // As it's an error scenario, the response body should be null
    }


    @Test
    void findAllSuccess() {
        List<AccountModel> mockAccountmodelList = new ArrayList<>();
        Mockito.when(accountRepository.findAll()).thenReturn(mockAccountmodelList);
        ResponseEntity<List<AccountModel>> response = accountService.findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccountmodelList, response.getBody());
    }

    @Test
    void findAllFailier() {
        Mockito.when(accountRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<List<AccountModel>> response = accountService.findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFindById_Success() {
        Long id = 1L;
        Optional<AccountModel> mockAccountModel = Optional.of(new AccountModel());
        Mockito.when(accountRepository.findById(id)).thenReturn(mockAccountModel);
        ResponseEntity<Optional<AccountModel>> response = accountService.findById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccountModel, response.getBody());
    }

    @Test
    public void testFindById_NotFound() {
        Long id = 1L;
        Mockito.when(accountRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<Optional<AccountModel>> response = accountService.findById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void updateAccountSuccess() {
        Long id = 1L;
        AccountModel newaccountM = new AccountModel();
        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(newaccountM));
        ResponseEntity<AccountModel> response = accountService.updateAccount(id, newaccountM);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    void updateAccountFail() {
        Long id = 1L;
        AccountModel newaccountM = new AccountModel();
        Mockito.when(accountRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<AccountModel> response = accountService.updateAccount(id, newaccountM);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void deleteAccountSuccess() {
        Long id = 1L;
        ResponseEntity<String> response = accountService.deleteAccount(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check the status code
        assertEquals("Deleted", response.getBody());
    }
    @Test
    void deleteAccountFail() {
        Long id = 1L;

        // Simulate the repository delete failure
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(accountRepository).deleteById(id);

        ResponseEntity<String> response = accountService.deleteAccount(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Error", response.getBody());
    }
}