package com.example.demo.resources;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/users")
public class UserResource {

    /*
        Essa classe disponibilizará um recurso Web correspondente à entidade User.
     */

    // Criamos um método que será um endpoint para acessar os usuários
    // Inicialmente, faremos uma implementação básica para teste. Mais para frente,
    //   melhoraremos isso.

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        /*
            Esse método retorna o tipo 'ResponseEntity<T>' que é um tipo específico do Spring para retornar
                respostas de requisições Web.
            A annotation 'GetMapping' especifica que esse método responde a uma requisição do tipo GET do HTTP.
         */

        // User u = new User(1L, "Maria", "maria@gmail.com", "99999", "12345");
        /*
            A linha comentada acima deve-se à criação do método findAll() em UserService.
            O código abaixo é a atualização demandada no UserService.
            Também é demanda do UserService uma injeção de dependência como 'private UserService service;'
                colocada antes do @GetMapping
         */

        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // O endpoint abaixo encontra um usuário por Id no banco de dados
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // Criando o endpoint para a inserção de um usuário. Note que não usaremos a operação GET do HTTP, como fizemos nos endpoints acima
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        // return ResponseEntity.ok().body(obj);  // Provisoriamente na aula 324 (esse return devolve um código HTTP 200,
                                                  //   e o melhor para nós é que seja o 201)

        // A diferença é que para retornar o código 201, precisamos de uma URI com um cabeçalho, como fazemos abaixo:
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }


}
