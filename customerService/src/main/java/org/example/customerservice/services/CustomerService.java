package org.example.customerservice.services;

import org.example.customerservice.entity.Customer;
import org.example.customerservice.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public Customer getCustomerById(Long id){
        return customerRepo.findById(id).orElseThrow(()->
                new RuntimeException("Customer with id "+id+" not found")
                );
    }
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer findByName(String name){
        return customerRepo.findAllByName(name).get(0);
    }
    public Customer findByEmail(String email){
        return customerRepo.findByEmail(email).orElseThrow(()->
                new RuntimeException("Customer with email "+email+" not found")
                );
    }
}

