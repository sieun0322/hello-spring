package com.example.catalogservice.messagequeue;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    CatalogRepository repository;

    @Autowired
    public KafkaConsumer(CatalogRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics="example-catalog-topic")
    public void updateQty(String kafkaMessage){
        log.info("Kafka message"+kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map =mapper.readValue(kafkaMessage, new TypeReference< Map<Object, Object>>(){});

        } catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        CatalogEntity entity = repository.findByProductId((String)map.get("productId"));
        if(entity != null){
            entity.setStock( entity.getStock() - (Integer)map.get("qty"));
            repository.save(entity);
        }
    }

}
