package com.bank.Bank.Repository;

import com.bank.Bank.Model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankModel,Long> {
}