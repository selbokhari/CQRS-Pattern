package com.food.ordering.system.customer.service.rest;

import com.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;
import com.food.ordering.system.customer.service.domain.ports.input.service.CustomerServiceApplication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerServiceApplication customerServiceApplication;

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerCommand createCustomerCommand) {
        log.info("Creating customer with username: {}", createCustomerCommand.getUsername());
        CreateCustomerResponse createCustomerResponse = customerServiceApplication.createCustomer(createCustomerCommand);
        return ResponseEntity.ok(createCustomerResponse);
    }
}
