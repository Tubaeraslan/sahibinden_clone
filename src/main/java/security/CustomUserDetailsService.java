package security;

import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Tüm kullanıcıları al
        List<User> users = userRepository.findAll();

        // 2. Liste içinde username eşleşmesini bul
        User matchedUser = users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 3. ID’sini al (istersen burada kullanabilirsin)
        Integer userId = matchedUser.getId();

        // 4. UserDetails oluştur
        return org.springframework.security.core.userdetails.User
                .withUsername(matchedUser.getUserName())
                .password(matchedUser.getPassword()) // password bcrypt olmalı
                .authorities("USER")
                .build();
    }
}
