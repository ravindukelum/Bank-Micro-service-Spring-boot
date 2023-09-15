package com.bank.Bank.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_type")
public class AccountTypeModel {
    @Id
    private Long accountTypeId;

    @NotNull(message = "Account Type cannot be empty")
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "description")
    private String description;
    @Positive(message = "Interest rate should be positive")
    @Column(name = "interest_rate")
    private Double interestRate;


}
