package com.example.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory

@Configuration
@EnableKafka
class KafkaConfig {
    companion object {
        const val bootstrapServer = "localhost:9092"
    }

    @Bean //프로듀서 설정 추가하여 빈 등록
    fun producerFactory(): ProducerFactory<String, String> {
        val configurationProperties = HashMap<String, Any>()
        configurationProperties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        configurationProperties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configurationProperties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return DefaultKafkaProducerFactory(configurationProperties)
    }

    @Bean //프로듀서 설정 및 빈등록

    fun consumerFactory(): ConsumerFactory<String, String>{
        val configurationProperties = HashMap<String, Any>()
        configurationProperties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        configurationProperties[ConsumerConfig.GROUP_ID_CONFIG] = "fintech"
        configurationProperties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        configurationProperties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configurationProperties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    }
}