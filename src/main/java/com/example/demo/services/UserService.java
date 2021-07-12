package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        // return obj.get();  // Linha comentada na aula 327, tratamento de exceções do findById
        /*
            Com esse método, eu devo atualizar o UserResource.
         */
        // A seguir, a atualizaçãon da linha de retorno
        return obj.orElseThrow( () -> new ResourcesNotFoundException(id) );
    }

    // Vamos salvar no banco de dados um dado usuário (ver comentário em UserResource)
    public User insert(User obj) {
        return repository.save(obj);
    }

    // Vamos deletar um usuário do banco de dados usando seu ID
    public void delete(Long id) {
        // repository.deleteById(id); // Implementação temporária da aula 325
        // Abaixo, implementação com tratamento de exceção da aula 328
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new ResourcesNotFoundException(id);
            // A exceção 'EmptyResultDataAccessException' foi capturada depois de verificarmos imprimindo na tela
            //  com o comando 'catch (RuntimeException e) {e.printStackTrace}' e fazendo a deleção no postman
            //  Essa metodologia de capturar os erros específicos serve para muitas coisas, e usamos de novo abaixo,
            //  para o erro específico do framework spring para quando tentamos deletar um usuário com pendências.
        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException(e.getMessage());
            // Aqui, no entanto, criaremos uma exceção específica do banco de dados, criando uma nova classe de exceção
            //   em serviços: 'DatabaseException'. Essa é uma excfeção da nossa camada de serviços.
        }
    }

    // Para atualizar um dado usuário
    public User update(Long id, User obj) {
        // Tratamento de exceção da aula 329
        try {
            User entity = repository.getById(id); // Na aula, o prof. manda usar o getOne(), mas ele está depreciado e devemos usar o getById().
            updateData(entity, obj); // Função a ser criada abaixo
            return repository.save(entity);
        } catch (EntityNotFoundException e) {

            throw new ResourcesNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {

        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());

    }


}
