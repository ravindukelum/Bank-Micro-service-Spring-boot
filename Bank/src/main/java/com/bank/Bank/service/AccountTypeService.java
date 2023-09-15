package com.bank.Bank.service;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeService {

    @Autowired
    AccountTypeRepository accountTypeRepository;



    //save
    public ResponseEntity<AccountTypeModel> create(AccountTypeModel accountTypeModel) {
        try {
            AccountTypeModel saveAccountTypeModel = accountTypeRepository
                    .save(accountTypeModel);
            return new ResponseEntity<>(saveAccountTypeModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //find all

    public ResponseEntity<List<AccountTypeModel>> findAll(){
        try {
            List<AccountTypeModel> a= accountTypeRepository.findAll();
            return new ResponseEntity<>(a,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //find by id
    public  ResponseEntity<Optional<AccountTypeModel>> findById(Long id){
        try {
            Optional<AccountTypeModel> accountTypeModel= accountTypeRepository.findById(id);
            return new ResponseEntity<>(accountTypeModel,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //update

    public ResponseEntity<AccountTypeModel> update(Long id,AccountTypeModel newAccountTypeModel){
        try {
            AccountTypeModel updateATM= accountTypeRepository.findById(id).orElseThrow();
            updateATM.setAccountType(newAccountTypeModel.getAccountType());
            updateATM.setDescription(newAccountTypeModel.getDescription());
            updateATM.setInterestRate(newAccountTypeModel.getInterestRate());
            accountTypeRepository.save(updateATM);

            return ResponseEntity.ok(updateATM);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //delete

    public ResponseEntity<String> delete(Long id) {
        try {
            accountTypeRepository.deleteById(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
