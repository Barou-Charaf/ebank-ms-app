package org.example.bankaccountservice.controller;

import org.example.bankaccountservice.dtos.CreateAccountRequest;
import org.example.bankaccountservice.entity.BankAccountResponse;
import org.example.bankaccountservice.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccountResponse> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccountResponse getBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccount(id);
    }
    @PostMapping
    public BankAccountResponse createAccount(CreateAccountRequest request){
        return bankAccountService.createBankAccount(request);
    }
}
