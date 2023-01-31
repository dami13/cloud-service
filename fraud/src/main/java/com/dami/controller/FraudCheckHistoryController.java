package com.dami.controller;

import com.dami.model.FraudCheckResponse;
import com.dami.service.FraudCheckHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(fraudCheckHistoryService.isFraudulentCustomer(customerId));
    }
}
