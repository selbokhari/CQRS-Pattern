package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.exception.DomainException;
import com.food.ordering.system.order.service.domain.dto.message.CustomerModel;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.customer.CustomerMessageListener;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerMessageListenerImpl implements CustomerMessageListener {

    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public void customerCreated(CustomerModel customerModel) {
        Customer customer = orderDataMapper.customerModelToCustomer(customerModel);
        Customer createdCustomer = customerRepository.save(customer);
        if (createdCustomer == null) {
            log.error("Customer could not be created in order database with id: {} ", customerModel.getId());
            throw new DomainException(String.format("Customer could not be created in order database with id: %s ", customerModel.getId()));
        }
    }
}
