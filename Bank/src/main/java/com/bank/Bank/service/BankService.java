package com.bank.Bank.service;

import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;


    //create
    public ResponseEntity<BankModel> createbank( BankModel bankModel){
        try {
            bankRepository.save(bankModel);
            return ResponseEntity.ok(bankModel);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    //find all

    public ResponseEntity<List<BankModel>> findBankAccounts(){
        try {
            List<BankModel> allBanks= bankRepository.findAll();
            return ResponseEntity.ok(allBanks);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //find by id


    public ResponseEntity<Optional<BankModel>> findBankAccountUsingId(Long bankId){
        try {
            Optional<BankModel> bank=bankRepository.findById(bankId);
            return ResponseEntity.ok(bank);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //update

    public ResponseEntity<BankModel> UpdateBank(Long bankId,BankModel bankModel){
        try {
            BankModel banknew=bankRepository.findById(bankId).orElseThrow();
            banknew.setName(bankModel.getName());
            banknew.setBranch(bankModel.getBranch());
            bankRepository.save(banknew);

            return  ResponseEntity.ok(banknew);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //delete

    public ResponseEntity<String> DeleteBank(Long bankId){
        try {
            bankRepository.deleteById(bankId);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
