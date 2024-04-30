package com.food.ordering.system.customer.service.dataaccess.adapter;

import com.food.ordering.system.customer.service.dataaccess.entity.CustomerEntity;
import com.food.ordering.system.customer.service.dataaccess.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.customer.service.dataaccess.repository.CustomerJpaRepository;
import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.customer.service.domain.ports.output.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity customerEntity = customerDataAccessMapper.customerToCustomerEntity(customer);
        CustomerEntity createdCustomerEntity = customerJpaRepository.save(customerEntity);
        return customerDataAccessMapper.customerEntityToCutomer(createdCustomerEntity);
    }

}
