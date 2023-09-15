package com.bank.Bank.Repository;

import com.bank.Bank.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Long> {

}
