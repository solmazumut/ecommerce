package com.microservices.ecommerce.basket.service.config;

import com.microservices.ecommerce.basket.service.model.event.*;
import com.microservices.ecommerce.basket.service.utils.JsonSerializerWithJTM;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class KafkaConfig {

    private String kafkaAddress = "http://localhost:9092";

    //Produce
    @Bean
    public ProducerFactory producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public KafkaTemplate<String, UserMadeBasketOperation> kafkaTemplateUserMadeBasketOperation() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, NotificationEvents> kafkaTemplateNotificationEvents() {
        return new KafkaTemplate<>(producerFactory());
    }



    //consume
    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 25000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 35000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductPriceChanged> productPriceChangedQueueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductPriceChanged> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(ProductPriceChanged.class,false)));
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, ProductPriceChanged> productPriceChangedKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(consumerConfigs()));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductStockChanged> productStockChangedQueueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductStockChanged> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(ProductStockChanged.class,false)));
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, ProductStockChanged> productStockChangedKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(consumerConfigs()));
    }



}
