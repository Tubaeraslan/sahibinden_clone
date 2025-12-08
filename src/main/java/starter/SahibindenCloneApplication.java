package starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"entities","common"})
@ComponentScan(basePackages = {"starter", "controller", "service", "repository" , "exception","mapper","web","security","log"})
@EnableJpaRepositories(basePackages = {"repository"})
@EnableJpaAuditing
public class SahibindenCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahibindenCloneApplication.class, args);
	}
}
