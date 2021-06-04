package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

/*
    Com somente essa codificação, seremos capazes de instanciar um objeto Repository
        que vai ter várias operações para trabalharmos com o usuário.
    Nesse caso, não precisamos implementar nada, pois o Spring Data JPA tem uma implementação
        padrão para quando fornecermos a classe (User) e o tipo do ID da entidade (Long)
 */