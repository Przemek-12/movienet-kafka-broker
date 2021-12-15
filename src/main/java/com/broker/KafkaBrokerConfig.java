package com.broker;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaBrokerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    // A KafkaAdmin bean is responsible for creating new
    // topics in our broker. With Spring Boot, a KafkaAdmin bean is automatically
    // registered.
    @Bean
    public NewTopic userDeleted() {
        return TopicBuilder.name("user-deleted")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userAdded() {
        return TopicBuilder.name("user-added")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic videoDeleted() {
        return TopicBuilder.name("video-deleted")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic videoAdded() {
        return TopicBuilder.name("video-added")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
