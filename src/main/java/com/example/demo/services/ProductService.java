package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    O @Service serve para registrar o ProductService no Spring (é o 'component registration da aula').
        Alternativamente, poderíamos colocar @Component, mas o prof. Nélio resolveu deixar mais semanticamente
        consistente.
        (Ver comentário em ProductRepository sobre 'component registration.')
 */

@Service
public class ProductService {

    // Declarando a dependência para o Product Repository
    @Autowired
    private ProductRepository repository;

    // Criação de um método para recuperar todos os usuários no banco de dados
    public List<Product> findAll() {
        return repository.findAll();
        /*
            Com esse método, eu devo atualizar o ProductResource.
         */
    }

    // Criação de um método para recuperar um usuário no banco de dados po ID
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
        /*
            Com esse método, eu devo atualizar o ProductResource.
         */
    }
}
