package com.cosumer.kafkaconsumer;


import com.cosumer.model.Customer;
import com.cosumer.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    @Autowired
    CustomerRepository customerRepository;


    @KafkaListener(topics = "NewTopic", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Customer[] customer = objectMapper.readValue(message, Customer[].class);
            List<Customer> customers = Arrays.asList(customer);
            customerRepository.saveAll(customers);
            System.out.println("message = " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
