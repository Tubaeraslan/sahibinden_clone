package security;

import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import repository.UserRepository;

import java.util.List;

//@Component
//@RequiredArgsConstructor
//public class PasswordHashUpdater implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            // Eğer şifre zaten BCrypt ile hashlenmiş değilse
//            if (!user.getPassword().startsWith("$2a$")) {
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                userRepository.save(user);
//            }
//        }
//        System.out.println("Eski kullanıcı şifreleri hashlendi!");
//    }
//}
