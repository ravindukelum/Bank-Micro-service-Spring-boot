package com.bank.Bank.service;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    //save
    public ResponseEntity<AccountModel> createAccount(AccountModel accountModel) {
        try {
            AccountModel createAccount = accountRepository.save(accountModel);
            return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //find all

    public ResponseEntity<List<AccountModel>> findAll() {
        try {
            List<AccountModel> a = accountRepository.findAll();
            return new ResponseEntity<>(a, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //find by id

    public ResponseEntity<Optional<AccountModel>> findById(Long accountId) {
        try {
                Optional<AccountModel> accountDetails = accountRepository.findById(accountId);
                return ResponseEntity.ok(accountDetails);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



    //update
    public ResponseEntity<AccountModel> updateAccount(Long accountId, AccountModel accountModel) {
        try {
            AccountModel newaccountM = accountRepository.findById(accountId).orElseThrow(null);
            newaccountM.setBalance(accountModel.getBalance());
            newaccountM.setOpenDate(accountModel.getOpenDate());
            newaccountM.setBankModel(accountModel.getBankModel());
            newaccountM.setAccountTypeModel(accountModel.getAccountTypeModel());
            newaccountM.setCustomerModel(accountModel.getCustomerModel());

            accountRepository.save(newaccountM);

            return ResponseEntity.ok(newaccountM);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    //delete

    public ResponseEntity<String> deleteAccount(Long accountId) {
        try {
            accountRepository.deleteById(accountId);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }
}
