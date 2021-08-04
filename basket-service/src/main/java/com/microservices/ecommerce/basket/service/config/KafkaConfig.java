package com.microservices.ecommerce.basket.service.config;

import com.microservices.ecommerce.basket.service.model.event.UserMadeAddBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeDeleteBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeUpdateBasketOperation;
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

    @Bean
    public KafkaTemplate<String, UserMadeBasketOperation> kafkaTemplateUserMadeBasketOperation() {
        return new KafkaTemplate<>(producerFactory());
    }

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
    public ConcurrentKafkaListenerContainerFactory<String, UserMadeBasketOperation> userMadeBasketOperationQueueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserMadeBasketOperation> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(UserMadeBasketOperation.class,false)));
        factory.setBatchListener(true);
        return factory;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializerWithJTM.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 25000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 35000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        return props;
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserMadeBasketOperation> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserMadeBasketOperation> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UserMadeBasketOperation> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, UserMadeBasketOperation.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "basket");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }




}
