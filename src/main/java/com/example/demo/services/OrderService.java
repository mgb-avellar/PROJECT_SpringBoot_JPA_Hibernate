package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    O @Service serve para registrar o UserService no Spring (é o 'component registration da aula').
        Alternativamente, poderíamos colocar @Component, mas o prof. Nélio resolveu deixar mais semanticamente
        consistente.
        (Ver comentário em UserRepository sobre 'component registration.')
 */

@Service
public class OrderService {

    // Declarando a dependência para o Order Repository
    @Autowired
    private OrderRepository repository;

    // Criação de um método para recuperar todos os pedidos no banco de dados
    public List<Order> findAll() {
        return repository.findAll();
        /*
            Com esse método, eu devo atualizar o OrderResource.
         */
    }

    // Criação de um método para recuperar um pedido no banco de dados po ID
    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
        /*
            Com esse método, eu devo atualizar o OrderResource.
         */
    }
}
