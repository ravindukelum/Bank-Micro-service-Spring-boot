package com.bank.Bank.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@Entity
@Table(name = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class BankModel {
    @Id
    private Long bankId;

    @NotNull(message = "Please provide bank name")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Bank name contain characters only")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Please provide bank Branch Name")
    @Size(max = 20, message = "Branch name can't be more than 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Branch can contain characters and numbers only")
    @Column(name = "branch")
    private String branch;
}
