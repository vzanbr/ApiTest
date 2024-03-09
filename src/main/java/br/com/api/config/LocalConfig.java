package br.com.api.config;

import br.com.api.domain.User;
import br.com.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        User u1 = new User(null, "Gabriel", "gabriel@gmail.com", "123");
        User u2 = new User(null, "Carlos", "carlos@gmail.com", "123");

        userRepository.saveAll(List.of(u1,u2));
    }
}
