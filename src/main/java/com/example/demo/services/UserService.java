package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
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
public class UserService {

    // Declarando a dependência para o User Repository
    @Autowired
    private UserRepository repository;

    // Criação de um método para recuperar todos os usuários no banco de dados
    public List<User> findAll() {
        return repository.findAll();
        /*
            Com esse método, eu devo atualizar o UserResource.
         */
    }

    // Criação de um método para recuperar um usuário no banco de dados po ID
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
        /*
            Com esse método, eu devo atualizar o UserResource.
         */
    }

    // Vamos salvar no banco de dados um dado usuário (ver comentário em UserResource)
    public User insert(User obj) {
        return repository.save(obj);
    }

    // Vamos deletar um usuário do banco de dados usando seu ID
    public void delete(Long id) {
        repository.deleteById(id); // Implementação temporária da aula 325
    }

    // Para atualizar um dado usuário
    public User update(Long id, User obj) {
        User entity = repository.getById(id); // Na aula, o prof. manda usar o getOne(), mas ele está depreciado e devemos usar o getById().
        updateData(entity, obj); // Função a ser criada abaixo
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {

        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());

    }


}
