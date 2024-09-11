    package com.example.kafka.config

    import org.apache.kafka.clients.consumer.ConsumerConfig
    import org.apache.kafka.clients.producer.ProducerConfig
    import org.apache.kafka.common.serialization.StringDeserializer
    import org.apache.kafka.common.serialization.StringSerializer
    import org.springframework.context.annotation.Bean
    import org.springframework.context.annotation.Configuration
    import org.springframework.kafka.annotation.EnableKafka
    import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
    import org.springframework.kafka.core.*

    @Configuration
    @EnableKafka
    class KafkaConfig {
        // 복잡한 환경인 경우 프로퍼티로 가져오면 좋음
        companion object {
            const val bootstrapServer = "kafka:9092"
        }

        @Bean //프로듀서 설정 추가하여 빈 등록
        fun producerFactory(): ProducerFactory<String, String> {
            val configurationProperties = HashMap<String, Any>()
            configurationProperties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
            configurationProperties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            configurationProperties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            configurationProperties[ProducerConfig.ACKS_CONFIG] = "all"

            configurationProperties[ProducerConfig.BATCH_SIZE_CONFIG] = 16384 // 16KB
            configurationProperties[ProducerConfig.LINGER_MS_CONFIG] = 5 // 5ms

            configurationProperties[ProducerConfig.BUFFER_MEMORY_CONFIG] = 33554432 // 32MB
            configurationProperties[ProducerConfig.RETRIES_CONFIG] = 3 // 최대 3번 재시도
            configurationProperties[ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION] = 5



            return DefaultKafkaProducerFactory(configurationProperties)
        }

        @Bean //컨슈머 설정 및 빈등록
        fun consumerFactory(): ConsumerFactory<String, String> {
            val configurationProperties = HashMap<String, Any>()
            configurationProperties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
            configurationProperties[ConsumerConfig.GROUP_ID_CONFIG] = "fintech" // 컨슈머 그룹 내에서 오프셋을 관리하므로 그룹 지정
//            configurationProperties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest" //오프셋 설정
            configurationProperties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "latest"
            configurationProperties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            configurationProperties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java

            return DefaultKafkaConsumerFactory(configurationProperties)
        }

        @Bean //카프카 토픽을 발행하기 위해 설정
        fun kafkaTemplate(): KafkaTemplate<String, String> {
            return KafkaTemplate(producerFactory())
        }

        // 컨슈머 설정 - 토픽에 리스너 달아주기
        @Bean
        fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
            val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
            factory.consumerFactory = consumerFactory()
            return factory
        }
    }