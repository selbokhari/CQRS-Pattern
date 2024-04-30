package com.food.ordering.system.customer.service.messaging.publisher.kafka;

import com.food.ordering.system.customer.service.domain.config.CustomerServiceConfigData;
import com.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;
import com.food.ordering.system.customer.service.domain.ports.output.message.publisher.CustomerMessagePublisher;
import com.food.ordering.system.customer.service.messaging.mapper.CustomerMessagingDataMapper;
import com.food.ordering.system.kafka.order.avro.model.CustomerAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerCreatedEventKafkaPublisher implements CustomerMessagePublisher {

    private final CustomerMessagingDataMapper customerMessagingDataMapper;
    private final KafkaProducer<String, CustomerAvroModel> kafkaProducer;
    private final CustomerServiceConfigData customerServiceConfigData;

    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Received CustomerCreatedEvent for customer id {}", customerCreatedEvent.getCustomer().getId());
        try {
            CustomerAvroModel customerAvroModel = customerMessagingDataMapper.customerCreatedEventToCutomerAvroModel(customerCreatedEvent);
            kafkaProducer.send(customerServiceConfigData.getCustomerTopicName(),
                    customerAvroModel.getId(),
                    customerAvroModel,
                    getCallback("", customerServiceConfigData.getCustomerTopicName())
            );

        } catch (Exception ex) {
            log.error("Error while sending CustomerCreatedEvent to kafka for Customer id: {}", customerCreatedEvent.getCustomer().getId().getValue(), ex);
        }
    }

    private ListenableFutureCallback<SendResult<String, CustomerAvroModel>> getCallback(
            String message, String topicName) {
        return new ListenableFutureCallback<SendResult<String, CustomerAvroModel>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending the message {} to topic {}", message, topicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, CustomerAvroModel> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("received new meta data. Topic: {}; Partition: {}; Offset {}, Timestamp: {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime()
                );
            }
        };
    }
}
