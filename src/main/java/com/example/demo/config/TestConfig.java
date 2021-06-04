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

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
    }

}
