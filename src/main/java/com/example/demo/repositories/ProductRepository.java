package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    Eu poderia colocar aqui a anotação @Repository (ver comentário em ProductService) para
        fazer um registro de componente ('component registration'). No entanto, neste caso
        específico, não preciso, pois ProductRepository estende o JpaRepository que já está
        registrado no Spring.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}

/*
    Com somente essa codificação, seremos capazes de instanciar um objeto Repository
        que vai ter várias operações para trabalharmos com o usuário.
    Nesse caso, não precisamos implementar nada, pois o Spring Data JPA tem uma implementação
        padrão para quando fornecermos a classe (Product) e o tipo do ID da entidade (Long)
 */