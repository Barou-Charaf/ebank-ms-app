package org.example.bankaccountservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankaccountservice.enums.AccountType;
import org.example.bankaccountservice.modals.Customer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private AccountType accountType;
    private double balance;
    private long customerId;

    @Transient
    private Customer customer;
}
