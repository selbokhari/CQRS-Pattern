package com.food.ordering.system.customer.service.dataaccess.mapper;

import com.food.ordering.system.customer.service.dataaccess.entity.CustomerEntity;
import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCutomer(CustomerEntity customerEntity) {
        return new Customer(
                new CustomerId(customerEntity.getId()),
                customerEntity.getUsername(),
                customerEntity.getFirstName(),
                customerEntity.getLastName());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return new CustomerEntity(
                customer.getId().getValue(),
                customer.getUsername(),
                customer.getFirstname(),
                customer.getLastName()
        );
    }

}
