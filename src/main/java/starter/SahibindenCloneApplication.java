package starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = {"entities","common"})
@ComponentScan(basePackages = {"starter", "controller", "service", "repository" , "exception","mapper","web","security","log","job"})
@EnableJpaRepositories(basePackages = {"repository"})
@EnableJpaAuditing
@EnableScheduling
public class SahibindenCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahibindenCloneApplication.class, args);
	}
}
