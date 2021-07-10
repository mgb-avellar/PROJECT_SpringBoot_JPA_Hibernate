package com.example.demo.resources;

import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/categories")
public class CategoryResource {

    /*
        Essa classe disponibilizará um recurso Web correspondente à entidade Category.
     */

    // Criamos um método que será um endpoint para acessar os usuários
    // Inicialmente, faremos uma implementação básica para teste. Mais para frente,
    //   melhoraremos isso.

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {

        /*
            Esse método retorna o tipo 'ResponseEntity<T>' que é um tipo específico do Spring para retornar
                respostas de requisições Web.
            A annotation 'GetMapping' especifica que esse método responde a uma requisição do tipo GET do HTTP.
         */

        // Category u = new Category(1L, "Maria", "maria@gmail.com", "99999", "12345");
        /*
            A linha comentada acima deve-se à criação do método findAll() em CategoryService.
            O código abaixo é a atualização demandada no CategoryService.
            Também é demanda do CategoryService uma injeção de dependência como 'private CategoryService service;'
                colocada antes do @GetMapping
         */

        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // O endpoint abaixo encontra um usuário por Id no banco de dados
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
