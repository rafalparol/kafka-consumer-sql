package inc.temp.right.always.kafkaconsumersql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "inc.temp.right.always.kafkaconsumersql.repositories")
public class KafkaConsumerSqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerSqlApplication.class, args);
	}
}
