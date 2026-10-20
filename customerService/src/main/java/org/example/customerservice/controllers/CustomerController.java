package org.example.customerservice.controllers;

import org.example.customerservice.entity.Customer;
import org.example.customerservice.repos.CustomerRepo;
import org.example.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll(){
       return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @GetMapping("/name={name}")
    public Customer findByName(@PathVariable String name){
        return customerService.findByName(name);
    }
    @GetMapping("/email={email}")
    public Customer findByEmail(@PathVariable String email){
        return customerService.findByEmail(email);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

}
