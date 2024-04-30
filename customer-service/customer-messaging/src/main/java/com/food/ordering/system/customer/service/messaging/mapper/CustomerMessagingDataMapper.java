package com.food.ordering.system.customer.service.messaging.mapper;

import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;
import com.food.ordering.system.kafka.order.avro.model.CustomerAvroModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMessagingDataMapper {

    public CustomerAvroModel customerCreatedEventToCutomerAvroModel(CustomerCreatedEvent customerCreatedEvent) {
        Customer customer = customerCreatedEvent.getCustomer();
        return CustomerAvroModel.newBuilder()
                .setId(customer.getId().getValue().toString())
                .setUsername(customer.getUsername())
                .setFirstname(customer.getFirstname())
                .setLastName(customer.getLastName())
                .build();
    }

}
