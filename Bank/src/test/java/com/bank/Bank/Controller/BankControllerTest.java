package com.bank.Bank.Controller;

import com.bank.Bank.Model.BankModel;
import com.bank.Bank.service.BankService;
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
class BankControllerTest {

    @InjectMocks
    BankController bankController;

    @Mock
    BankService bankService;

    @Test
    void createbank() {
        BankModel mockBankModel=new BankModel();
        Mockito.when(bankService.createbank(mockBankModel)).thenReturn(ResponseEntity.ok(mockBankModel));
        ResponseEntity<BankModel> response = bankController.createBank(mockBankModel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void findBankAccounts() {
        List<BankModel> mockBankModel=new ArrayList<>();
        Mockito.when(bankService.findBankAccounts()).thenReturn(ResponseEntity.ok(mockBankModel));
        ResponseEntity<List<BankModel>> response= bankController.findAllBanks();
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void findBankAccountUsingId() {
        Long id =1L;
        Optional<BankModel> mockBankModel= Optional.of(new BankModel());
        Mockito.when(bankService.findBankAccountUsingId(id)).thenReturn(ResponseEntity.ok(mockBankModel));
        ResponseEntity<Optional<BankModel>> response= bankController.findBankById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void updateBank() {
        Long id =1L;
        BankModel mockBankModel=new BankModel();
        Mockito.when(bankService.UpdateBank(id,mockBankModel)).thenReturn(ResponseEntity.ok(mockBankModel));
        ResponseEntity<BankModel> response=bankController.updateBank(id,mockBankModel);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteBank() {
        Long id=1L;
        Mockito.when(bankService.DeleteBank(id)).thenReturn(ResponseEntity.ok("Deleted"));
        ResponseEntity<String> response= bankController.deleteBank(id);
        assertNotNull(response);
        assertEquals("Deleted",response.getBody());
    }
}