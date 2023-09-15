package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountTypeRepository;
import com.bank.Bank.service.AccountTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/accountType")
public class AccountTypeController {

    @Autowired
    AccountTypeService accountTypeService;

    //save
    @PostMapping("/save")
    public ResponseEntity<AccountTypeModel> create(@Valid @RequestBody AccountTypeModel accountTypeModel) {
        return accountTypeService.create(accountTypeModel);
    }

    //find all
    @GetMapping("/findall")
    public ResponseEntity<List<AccountTypeModel>> findAll() {
        return accountTypeService.findAll();
    }


    //find by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<AccountTypeModel>> findById(@Positive(message = "Id must be positive") @PathVariable Long id) {
        return accountTypeService.findById(id);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<AccountTypeModel> update(@Positive(message = "Id must be positive") @PathVariable Long id, @Valid @RequestBody AccountTypeModel newAccountTypeModel) {
        return accountTypeService.update(id, newAccountTypeModel);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@Positive(message = "Id must be positive") @PathVariable Long id) {
        return accountTypeService.delete(id);
    }
}
