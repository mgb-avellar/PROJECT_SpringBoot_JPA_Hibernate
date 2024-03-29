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

import com.example.demo.entities.*;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.repositories.*;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

        Product p1 =  new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 =  new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 =  new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 =  new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 =  new Product(null, "Rails of Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // Abaixo, as associações muitos para muitos entre produtos e categorias

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); // Preciso salvar novamente os produtos, agora com as associações.

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        // Exemplo de inserção do pagamento da Order o1 (a única que está paga no exemplo acima)
        // Note a diferença em relação aos exemplos acima: para salvar um objeto dependente em uma relação um para um,
        //   não chamamos o repository do próprio objeto; fazemos a associação de mão dupla em memória com o comando
        //   'o1.setPayment(pay1)' e depois salvo o pedido novamente com o comando 'orderRepository.save(o1)'.
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }

}
