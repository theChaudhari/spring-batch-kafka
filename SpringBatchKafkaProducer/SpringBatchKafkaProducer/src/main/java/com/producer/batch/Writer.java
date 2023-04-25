package com.producer.batch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.model.Customer;
import com.producer.repository.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<Customer> {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    KafkaTemplate<String, List> kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    @Override
    public void write(List<? extends Customer> list) throws Exception {
//        customerRepository.saveAll(list);
//        System.out.println("List of size " + list.size());
        objectMapper.writeValueAsString(list);
        kafkaTemplate.send(TOPIC,list);

    }


}
