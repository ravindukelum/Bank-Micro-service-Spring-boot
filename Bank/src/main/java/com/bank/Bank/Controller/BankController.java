package com.bank.Bank.Controller;

import com.bank.Bank.Model.BankModel;
import com.bank.Bank.service.BankService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class BankController {

    @Autowired
    BankService bankService;

    //create
    @PostMapping("/create")
    public ResponseEntity<BankModel> createBank(@RequestBody @Valid BankModel bankModel) {
        return bankService.createbank(bankModel);
    }

    // Find all banks
    @GetMapping("/banks")
    public ResponseEntity<List<BankModel>> findAllBanks() {
        return bankService.findBankAccounts();
    }

    // Find bank by ID
    @GetMapping("/{bankId}")
    public ResponseEntity<Optional<BankModel>> findBankById(@Positive(message = "Id should be positive") @PathVariable Long bankId) {
        Optional<BankModel> bankModel = bankService.findBankAccountUsingId(bankId).getBody();
        if (bankModel != null) {
            return ResponseEntity.ok(bankModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update bank by ID
    @PutMapping("/{bankId}")
    public ResponseEntity<BankModel> updateBank(@Positive(message = "Id must be positive") @PathVariable Long bankId, @RequestBody @Valid BankModel bankModel) {
        BankModel updatedBank = bankService.UpdateBank(bankId, bankModel).getBody();
        if (updatedBank != null) {
            return ResponseEntity.ok(updatedBank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete bank by ID
    @DeleteMapping("/{bankId}")
    public ResponseEntity<String> deleteBank(@Positive(message = "Id must be positive") @PathVariable Long bankId) {
        return bankService.DeleteBank(bankId);

    }

}
