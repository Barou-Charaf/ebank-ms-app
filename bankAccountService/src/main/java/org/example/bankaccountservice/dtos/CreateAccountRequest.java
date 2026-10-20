package org.example.bankaccountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankaccountservice.enums.AccountType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountRequest {
    private AccountType accountType;
    private long customerId;
}
