package com.bank.Bank.service;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @InjectMocks
    BankService bankService;
    @Mock
    BankRepository bankRepository;

    @Test
    void createbankSuccess() {
        BankModel bankModelMock=new BankModel();
        Mockito.when(bankRepository.save(bankModelMock)).thenReturn(bankModelMock);
        ResponseEntity<BankModel> response=bankService.createbank(bankModelMock);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(bankModelMock,response.getBody());
    }

    @Test
    void createbankFail() {
        BankModel bankModelMock=new BankModel();
        Mockito.when(bankRepository.save(bankModelMock)).thenThrow(new RuntimeException());
        ResponseEntity<BankModel> response=bankService.createbank(bankModelMock);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findBankAccountsSuccess() {
        List<BankModel> bankModelsMock=new ArrayList<>();
        Mockito.when(bankRepository.findAll()).thenReturn(bankModelsMock);
        ResponseEntity<List<BankModel>> response= bankService.findBankAccounts();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(bankModelsMock,response.getBody());
    }
    @Test
    void findBankAccountsFail() {
        List<BankModel> bankModelsMock=new ArrayList<>();
        Mockito.when(bankRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<List<BankModel>> response= bankService.findBankAccounts();
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findBankAccountUsingIdSuccess() {
        Long id=1L;
        Optional<BankModel> bankModelMock = Optional.of(new BankModel());
        Mockito.when(bankRepository.findById(id)).thenReturn(bankModelMock);
        ResponseEntity<Optional<BankModel>> response=bankService.findBankAccountUsingId(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(bankModelMock,response.getBody());
    }
    @Test
    void findBankAccountUsingIdFail() {
        Long id=1L;
        BankModel bankModelMock =new BankModel();
        Mockito.when(bankRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<Optional<BankModel>> response=bankService.findBankAccountUsingId(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateBankSuccess() {
        Long id=1L;
        BankModel bankModelMock =new BankModel();
        Mockito.when(bankRepository.findById(id)).thenReturn(Optional.of(bankModelMock));
        ResponseEntity<BankModel> resonse=bankService.UpdateBank(id,bankModelMock);
        assertNotNull(resonse);
        assertEquals(HttpStatus.OK,resonse.getStatusCode());
        assertEquals(bankModelMock,resonse.getBody());
    }
    @Test
    void updateBankFail() {
        Long id=1L;
        BankModel bankModelMock =new BankModel();
        Mockito.when(bankRepository.findById(id)).thenThrow(new RuntimeException());
        ResponseEntity<BankModel> resonse=bankService.UpdateBank(id,bankModelMock);
        assertNotNull(resonse);
        assertEquals(HttpStatus.NOT_FOUND,resonse.getStatusCode());
        assertNull(resonse.getBody());
    }

    @Test
    void deleteBankSuccess() {
        Long id=1L;
        Mockito.doNothing().when(bankRepository).deleteById(id);
        ResponseEntity<String> response = bankService.DeleteBank(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }
    @Test
    void deleteBankFail() {
        Long id=1L;
        Mockito.doThrow(new RuntimeException()).when(bankRepository).deleteById(id);
        ResponseEntity<String> response = bankService.DeleteBank(id);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}