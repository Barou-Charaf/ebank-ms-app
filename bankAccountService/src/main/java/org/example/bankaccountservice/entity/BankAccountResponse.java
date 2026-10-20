package org.example.bankaccountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankaccountservice.enums.AccountType;
import org.example.bankaccountservice.modals.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountResponse {
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private Customer customer;
}
