package com.bank.Bank.Repository;

import com.bank.Bank.Model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountRepository extends JpaRepository<AccountModel,Long> {
    AccountModel save(AccountModel accountModel);
}
