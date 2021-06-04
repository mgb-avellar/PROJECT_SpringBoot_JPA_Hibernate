package com.example.demo.resources;

import com.example.demo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/users")
public class UserResource {

    /*
        Essa classe disponibilizará um recurso Web correspondente à entidade User.
     */

    // Criamos um método que será um endpoint para acessar os usuários
    // Inicialmente, faremos uma implementação básica para teste. Mais para frente,
    //   melhoraremos isso.

    @GetMapping
    public ResponseEntity<User> findAll() {

        /*
            Esse método retorna o tipo 'ResponseEntity<T>' que é um tipo específico do Spring para retornar
                respostas de requisições Web.
            A annotation 'GetMapping' especifica que esse método responde a uma requisição do tipo GET do HTTP.
         */

        User u = new User(1L, "Maria", "maria@gmail.com", "99999", "12345");
        return ResponseEntity.ok().body(u);
    }
}
