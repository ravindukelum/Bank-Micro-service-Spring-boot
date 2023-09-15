package com.bank.Bank.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;


@Validated
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountModel {
    @Id
    private Long accountId;
    @Positive(message = "Balance must be positive")
    private Double balance;

    @NotNull(message = "Open date cannot be null")
    private Date openDate;


    @JoinColumn(name = "bank_id")
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    private BankModel bankModel;


    @JoinColumn(name = "account_type_id")
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    private AccountTypeModel accountTypeModel;


    @JoinColumn(name = "customer_id")
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerModel customerModel;
}
