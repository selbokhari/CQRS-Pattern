package com.food.ordering.system.customer.service.domain;

import com.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;
import com.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;
import com.food.ordering.system.customer.service.domain.mapper.CustomerDataMapper;
import com.food.ordering.system.customer.service.domain.ports.input.service.CustomerServiceApplication;
import com.food.ordering.system.customer.service.domain.ports.output.message.publisher.CustomerMessagePublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerServiceApplication {

    private final CustomerCreateCommandHandler customerCreateCommandHandler;
    private CustomerMessagePublisher customerMessagePublisher;
    private final CustomerDataMapper customerDataMapper;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerCommand createCustomerCommand) {
        CustomerCreatedEvent customerCreatedEvent = customerCreateCommandHandler.createdCustomer(createCustomerCommand);
        customerMessagePublisher.publish(customerCreatedEvent);
        return customerDataMapper.customerToCreateCustomerResponse(customerCreatedEvent.getCustomer(),
                "Customer created successfully");
    }
}
