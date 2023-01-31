package com.dami.service;

import com.dami.model.FraudCheckHistory;
import com.dami.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckHistoryService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudulentCustomer(Integer customerId) {
        // todo implement validation

        fraudCheckHistoryRepository.save(
                new FraudCheckHistory()
                        .setCustomerId(customerId)
                        .setIsFraudster(false)
                        .setCreatedAt(LocalDateTime.now()));
        return false;
    }
}
