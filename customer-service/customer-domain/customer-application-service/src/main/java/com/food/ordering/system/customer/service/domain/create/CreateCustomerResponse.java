package com.food.ordering.system.customer.service.domain.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateCustomerResponse {

    @NotNull
    private final UUID customerId;

    @NotNull
    private final String message;

}
