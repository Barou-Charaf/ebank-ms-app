package org.example.bankaccountservice.services;

import org.example.bankaccountservice.dtos.CreateAccountRequest;
import org.example.bankaccountservice.entity.BankAccount;
import org.example.bankaccountservice.entity.BankAccountResponse;
import org.example.bankaccountservice.fieng.CustomerFeignParams;
import org.example.bankaccountservice.modals.Customer;
import org.example.bankaccountservice.repos.BankAccountRepo;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final CustomerFeignParams customerFeignParams;

    public BankAccountService(BankAccountRepo bankAccountRepo,CustomerFeignParams customerFeignParams) {
        this.bankAccountRepo = bankAccountRepo;
        this.customerFeignParams=customerFeignParams;
    }


    @McpTool(description = "Get bank Account by Id")
    public BankAccountResponse getBankAccount(@McpToolParam(description = "bank account id") String id) {
       BankAccount bk=  bankAccountRepo.findById(id).orElseThrow(()->new RuntimeException("Bank account not found"));
       return mapToBankAccountResponse(bk);
    }

    @McpTool(description = "Get all bank  Accounts")
    public List<BankAccountResponse> getAllBankAccounts() {
        return  bankAccountRepo.findAll().stream().map(bk->mapToBankAccountResponse(bk)).toList();
    }

    @McpTool(description = "create bank account for a customer ")
    public BankAccountResponse createBankAccount(@McpToolParam(
            description = "Account type wich is one of the Two a CURRENT_ACCOUNT or SAVINGS_ACCOUNT  and CustomerId ") CreateAccountRequest  createAccountRequest) {
        try {
            Customer customer = customerFeignParams.getCustomer(createAccountRequest.getCustomerId());
            BankAccount bankAccount = BankAccount.builder()
                    .accountType(createAccountRequest.getAccountType())
                    .customerId(createAccountRequest.getCustomerId())
                    .balance(0.0)
                    .build();
            return mapToBankAccountResponse(bankAccountRepo.save(bankAccount));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public BankAccountResponse mapToBankAccountResponse(BankAccount bankAccount) {
        Customer customer = customerFeignParams.getCustomer(bankAccount.getCustomerId());
        return BankAccountResponse.builder()
                .accountNumber(bankAccount.getId())
                .balance(bankAccount.getBalance())
                .accountType(bankAccount.getAccountType())
                .customer(customer)
                .build();
    }
}
