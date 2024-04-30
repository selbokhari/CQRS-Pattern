package com.food.ordering.system.customer.service.domain;

import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerServiceDomainServiceImpl implements CustomerDomainService {

    private final String ZONE_ID = "UTC";

    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        // Any business logic required o run for a customer creation
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of(ZONE_ID)));
    }

}
