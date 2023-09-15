package com.bank.Bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
@Validated
public class CustomerModel {
    @Id
    private long customerId;


    @Column(name = "name")
    @NotNull(message = "Name should not be empty")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Province name must contain only alphabetic characters")
    private String name;
    @NotBlank(message = "Address should not be empty")
    @Column(name = "address")
    private String address;
    @NotNull(message = "Nic should not be empty")
    @Column(name = "nic")
    private String nic;
    @Column(name = "phone")
    private Integer phone;

    @JsonIgnore
    @OneToMany(mappedBy = "customerModel")
    private List<AccountModel> accountModelList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private BankModel bankModel;
}
