package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    O @Service serve para registrar o CategoryService no Spring (é o 'component registration da aula').
        Alternativamente, poderíamos colocar @Component, mas o prof. Nélio resolveu deixar mais semanticamente
        consistente.
        (Ver comentário em CategoryRepository sobre 'component registration.')
 */

@Service
public class CategoryService {

    // Declarando a dependência para o Category Repository
    @Autowired
    private CategoryRepository repository;

    // Criação de um método para recuperar todos os usuários no banco de dados
    public List<Category> findAll() {
        return repository.findAll();
        /*
            Com esse método, eu devo atualizar o CategoryResource.
         */
    }

    // Criação de um método para recuperar um usuário no banco de dados po ID
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
        /*
            Com esse método, eu devo atualizar o CategoryResource.
         */
    }
}
