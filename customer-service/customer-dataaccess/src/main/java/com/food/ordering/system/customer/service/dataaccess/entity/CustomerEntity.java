package com.food.ordering.system.customer.service.dataaccess.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;

}
