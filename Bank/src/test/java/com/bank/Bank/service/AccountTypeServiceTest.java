package com.bank.Bank.service;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountTypeRepository;
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
class AccountTypeServiceTest {

    @InjectMocks
    AccountTypeService accountTypeService;

    @Mock
    AccountTypeRepository accountTypeRepository;

    @Test
    void createSuccess() {
        AccountTypeModel accountTypeModelmock=new AccountTypeModel();
        Mockito.when(accountTypeRepository.save(accountTypeModelmock)).thenReturn(accountTypeModelmock);
        ResponseEntity<AccountTypeModel> response=accountTypeService.create(accountTypeModelmock);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(accountTypeModelmock,response.getBody());
    }
    @Test
    void createFail() {
        AccountTypeModel accountTypeModelmock=new AccountTypeModel();
        Mockito.when(accountTypeRepository.save(accountTypeModelmock)).thenThrow(new RuntimeException());
        ResponseEntity<AccountTypeModel> response=accountTypeService.create(accountTypeModelmock);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findAllSuccess() {
        List<AccountTypeModel> accountTypeModelslist=new ArrayList<>();
        Mockito.when(accountTypeRepository.findAll()).thenReturn(accountTypeModelslist);
        ResponseEntity<List<AccountTypeModel>> response= accountTypeService.findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(accountTypeModelslist,response.getBody());
    }
    @Test
    void findAllFail() {
        Mockito.when(accountTypeRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<List<AccountTypeModel>> response= accountTypeService.findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findByIdSuccess() {
        Long id=1L;
        Optional<AccountTypeModel> accountTypeModel= Optional.of(new AccountTypeModel());
        Mockito.when(accountTypeRepository.findById(id)).thenReturn(accountTypeModel);
        ResponseEntity<Optional<AccountTypeModel>> response=accountTypeService.findById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(accountTypeModel,response.getBody());
    }
    @Test
    void findByIdFail() {
        Long id=1L;
        Mockito.when(accountTypeRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<Optional<AccountTypeModel>> response=accountTypeService.findById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateSuccess() {
        Long id=1L;
        AccountTypeModel newAccountTypeModel =new AccountTypeModel();
        Mockito.when(accountTypeRepository.findById(id)).thenReturn(Optional.of(newAccountTypeModel));
        ResponseEntity<AccountTypeModel> resonse=accountTypeService.update(id,newAccountTypeModel);
        assertNotNull(resonse);
        assertEquals(HttpStatus.OK,resonse.getStatusCode());
        assertEquals(newAccountTypeModel,resonse.getBody());
    }
    @Test
    void updateFail() {
        Long id=1L;
        AccountTypeModel newAccountTypeModel =new AccountTypeModel();
        Mockito.when(accountTypeRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<AccountTypeModel> resonse=accountTypeService.update(id,newAccountTypeModel);
        assertNotNull(resonse);
        assertEquals(HttpStatus.NOT_FOUND,resonse.getStatusCode());
        assertNull(resonse.getBody());
    }

    @Test
    void deleteSuccess() {
        Long id=1L;
        Mockito.doNothing().when(accountTypeRepository).deleteById(id);

        ResponseEntity<String> response = accountTypeService.delete(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
    }
    @Test
    void deleteFail() {
        Long id=1L;
        Mockito.doThrow(new RuntimeException()).when(accountTypeRepository).deleteById(id);

        ResponseEntity<String> response = accountTypeService.delete(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}