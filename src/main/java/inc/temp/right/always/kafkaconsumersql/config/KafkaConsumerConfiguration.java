package inc.temp.right.always.kafkaconsumersql.config;

import inc.temp.right.always.temperaturemodel.TemperatureMeasurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
    @Value("${main.config.kafka.offsets}")
    private String kafkaOffsets;
    @Value("${main.config.kafka.bootstrapAddress}")
    private String kafkaBootstrapAddress;
    @Value("${main.config.kafka.consumerGroupId}")
    private String kafkaConsumerGroupId;

    @Bean
    public ConsumerFactory<String, TemperatureMeasurement> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                kafkaOffsets);
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaBootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                kafkaConsumerGroupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TemperatureMeasurement>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TemperatureMeasurement> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}