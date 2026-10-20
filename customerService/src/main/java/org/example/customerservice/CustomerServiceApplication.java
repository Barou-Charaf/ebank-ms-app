package org.example.customerservice;

import org.example.customerservice.entity.Customer;
import org.example.customerservice.repos.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo) {
        return args -> {
            Customer customer = Customer.builder()
                    .name("John")
                    .email("john1@gmail.com")
                    .build();
            Customer customer1 = Customer.builder()
                    .name("reda")
                    .email("reda1@gmail.com")
                    .build();
            Customer customer2 = Customer.builder()
                    .name("omar")
                    .email("omar@gmail.com")
                    .build();
            Customer customer3 = Customer.builder()
                    .name("salma")
                    .email("salma@gmail.com")
                    .build();
            Customer customer4 = Customer.builder()
                    .name("hamza")
                    .email("hamza@gmail.com")
                    .build();
            Customer customer5 = Customer.builder()
                    .name("simo")
                    .email("youssfi@gmail.com")
                    .build();
            Customer customer6 = Customer.builder()
                    .name("simo")
                    .email("simo6@gmail.com")
                    .build();

            customerRepo.save(customer);
            customerRepo.save(customer1);
            customerRepo.save(customer2);
            customerRepo.save(customer3);
            customerRepo.save(customer4);
            customerRepo.save(customer5);
            customerRepo.save(customer6);
        };
    }

}
