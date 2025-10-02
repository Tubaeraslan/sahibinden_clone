package starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"entities"})
@ComponentScan(basePackages = {"starter", "controller", "service", "repository"})
@EnableJpaRepositories(basePackages = {"repository"})
@SpringBootApplication
public class SahibindenCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahibindenCloneApplication.class, args);
	}
}
