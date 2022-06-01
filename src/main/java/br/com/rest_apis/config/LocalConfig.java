package br.com.rest_apis.config;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        Usuario u1 = new Usuario(null, "Valdir", "valdir@mail.com", "123");
        Usuario u2 = new Usuario(null, "Luiz", "luiz@mail.com", "123");

         userRepository.save(u1);
        userRepository.save(u2);
    }
}