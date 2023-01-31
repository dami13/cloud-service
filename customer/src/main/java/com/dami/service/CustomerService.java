package com.dami.service;

import com.dami.model.Customer;
import com.dami.model.CustomerRegistrationRequest;
import com.dami.model.FraudCheckResponse;
import com.dami.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest crr) {
        Customer customer =
                new Customer()
                        .setFirstName(crr.firstName())
                        .setLastName(crr.lastName())
                        .setEmails(crr.email());

        // todo add validation
        customerRepository.saveAndFlush(customer);

        var response = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if (response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo send notification
    }
}
