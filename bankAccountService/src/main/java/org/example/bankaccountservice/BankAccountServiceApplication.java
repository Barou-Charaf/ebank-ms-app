package org.example.bankaccountservice;

import org.example.bankaccountservice.dtos.CreateAccountRequest;
import org.example.bankaccountservice.enums.AccountType;
import org.example.bankaccountservice.repos.BankAccountRepo;
import org.example.bankaccountservice.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner init(BankAccountService bankAccountService) {
        return args -> {
            Random random = new Random();
            for (int i = 1; i <= 6; i++) {
                for(int j = 1; j <= 3; j++) {
                    CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                            .accountType(random.nextDouble()<=0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVINGS_ACCOUNT)
                            .customerId(i)
                            .build();
                    bankAccountService.createBankAccount(createAccountRequest);
                }
            }
        };
    }
}
