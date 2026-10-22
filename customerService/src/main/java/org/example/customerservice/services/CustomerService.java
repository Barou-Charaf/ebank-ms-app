package org.example.customerservice.services;

import org.example.customerservice.dtos.CreateCustomerDto;
import org.example.customerservice.entity.Customer;
import org.example.customerservice.repos.CustomerRepo;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @McpTool(description = "use this tool to get a customer by id")
    public Customer getCustomerById(@McpToolParam(description = "customer id") Long id){
        return customerRepo.findById(id).orElseThrow(()->
                new RuntimeException("Customer with id "+id+" not found")
                );
    }

    @McpTool(description = "get all customers  in data base .")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @McpTool(description = "use this Tool to Create a new  Customer")
    public Customer createCustomer(@McpToolParam(description = "prodive an object with email and name attributes") CreateCustomerDto customer){
        return customerRepo.save(Customer.builder()
                        .name(customer.getName())
                        .email(customer.getEmail())
                         .build());
    }

    @McpTool(description = "find a Customer by name")
    public Customer findByName(@McpToolParam(description = "customer name ") String name){
        return customerRepo.findAllByName(name).get(0);
    }

    @McpTool(description = "find a customer by email")
    public Customer findByEmail(@McpToolParam(description = "customer email") String email){
        return customerRepo.findByEmail(email).orElseThrow(()->
                new RuntimeException("Customer with email "+email+" not found")
                );
    }
}

