package config;

import entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    //sonra tanımladığın listeleri sil databaseden çek artık

    @Bean
    public List<User> userList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "tuba", "tuba@example.com", "1234"));
        users.add(new User(2, "ali", "ali@example.com", "abcd"));
        users.add(new User(3, "zeynep", "zeynep@example.com", "pass"));
        users.add(new User(4, "ayşe", "ayşe@example.com", "0000"));
        return users;
    }

}
