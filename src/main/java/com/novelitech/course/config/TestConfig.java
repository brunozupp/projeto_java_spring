package com.novelitech.course.config;

import com.novelitech.course.entities.Category;
import com.novelitech.course.entities.Order;
import com.novelitech.course.entities.User;
import com.novelitech.course.enums.OrderStatus;
import com.novelitech.course.repositories.CategoryRepository;
import com.novelitech.course.repositories.OrderRepository;
import com.novelitech.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration // fala para o Spring que essa classe é de Configurações
@Profile("test") // identifica que faz parte do ambiente de test. Tem que colocar o valor igual ao colocado em application.application
public class TestConfig implements CommandLineRunner {

    @Autowired // Injeção de dependência
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Vai ser executado assim que a aplicação rodar
    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1,u2));

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1);

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
    }
}
