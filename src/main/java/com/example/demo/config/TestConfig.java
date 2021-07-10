package com.example.demo.config;

/*
    Esta classe de configurações nos ajudará a configurar nosso perfil de teste,
        conforme definidos nos arquivos 'application.properties' e 'application-test.proporties'.
    Para falar para o Spring que essa classe é uma classe específica de configuração, usamos
        a anotação @Configuration.
    Para instruir que as configurações são específicas para o perfil de teste, usamos a anotação
        @Profile("test"), onde 'test' é o mesmo nome do perfil como definido no arquivo
        'application.properties'.
 */

import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    /*
        Por enquanto (aula 311), essa classe será usada para fazer o 'database seeding',
            populando, assim, nosso banco de dados. Para isso, preciso da classe 'UserRepository'.
        Essa será nossa primeira injeção de dependência, qua as boas práticas dizem que precisa
            ser fraca (ou desacoplada). Um framework, como o Spring, tem um mecanismo de
            injeção de dependência.
     */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*
        Agora preciso instanciar os objetos e salvar no banco de dados. Para isso, usaremos
            o macete de fazer essa classe implementar o 'CommandLineRunner'. O @Override abaixo
            surge automaticamente depois do 'implements ...'
     */

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    }



}
