package com.food.ordering.system.customer.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private final String username;
    private final String firstname;
    private final String lastName;

    public Customer(CustomerId customerId, String username, String firstname, String lastName) {
        super.setId(customerId);
        this.username = username;
        this.firstname = firstname;
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }
}
