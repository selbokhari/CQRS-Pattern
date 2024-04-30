package com.food.ordering.system.customer.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "customer-service")
@Configuration
public class CustomerServiceConfigData {

    String customerTopicName;

}