package com.novelitech.course.config;

import com.novelitech.course.entities.User;
import com.novelitech.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // fala para o Spring que essa classe é de Configurações
@Profile("test") // identifica que faz parte do ambiente de test. Tem que colocar o valor igual ao colocado em application.application
public class TestConfig implements CommandLineRunner {

    @Autowired // Injeção de dependência
    private UserRepository userRepository;

    // Vai ser executado assim que a aplicação rodar
    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1,u2));
    }
}
