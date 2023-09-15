package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    @Autowired
    AccountService accountService;

    //save
    @PostMapping("/create")
    public ResponseEntity<AccountModel> createAccount(@Valid @RequestBody AccountModel accountModel) {
        return accountService.createAccount(accountModel);
    }

    //find all
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountModel>> findAll() {
        return accountService.findAll();
    }

    //find by id
    @GetMapping("find/{accountId}")
    public ResponseEntity<Optional<AccountModel>> findById(
            @PathVariable(required = true)
            @Positive(message = "Id must be positive")
            Long accountId
    ) {
        return accountService.findById(accountId);
    }

    //update
    @PutMapping("update/{accountId}")
    public ResponseEntity<AccountModel> updateAccount(@PathVariable @Positive(message = "Id must be positive") Long accountId, @Valid @RequestBody AccountModel accountModel) {
        return accountService.updateAccount(accountId, accountModel);
    }

    //delete

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(@Positive(message = "Id must be positive") @PathVariable Long accountId) {
        return accountService.deleteAccount(accountId);
    }
}
