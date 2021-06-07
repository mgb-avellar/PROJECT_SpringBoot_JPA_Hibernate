package com.example.demo.resources;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {

    /*
        Essa classe disponibilizará um recurso Web correspondente à entidade Order.
     */

    // Criamos um método que será um endpoint para acessar os pedidos
    // Inicialmente, faremos uma implementação básica para teste. Mais para frente,
    //   melhoraremos isso.

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {

        /*
            Esse método retorna o tipo 'ResponseEntity<T>' que é um tipo específico do Spring para retornar
                respostas de requisições Web.
            A annotation 'GetMapping' especifica que esse método responde a uma requisição do tipo GET do HTTP.
         */

        // Order u = new Order(1L, "Maria", "maria@gmail.com", "99999", "12345");
        /*
            A linha comentada acima deve-se à criação do método findAll() em OrderService.
            O código abaixo é a atualização demandada no OrderService.
            Também é demanda do OrderService uma injeção de dependência como 'private OrderService service;'
                colocada antes do @GetMapping
         */

        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // O endpoint abaixo encontra um usuário por Id no banco de dados
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
