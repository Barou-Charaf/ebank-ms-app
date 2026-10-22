package org.example.bankaccountservice.fieng;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.bankaccountservice.modals.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignParams {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustmerDetails")
    public Customer getCustomer(@PathVariable Long id);

    default Customer getDefaultCustmerDetails(Long id,Exception e){
        return new Customer(id,"Not Available","Not Available");
    }

}
