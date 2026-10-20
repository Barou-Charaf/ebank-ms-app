package org.example.bankaccountservice.fieng;

import org.example.bankaccountservice.modals.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignParams {
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id);
}
