package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Associação com a classe Product: aula 317
    //@Transient // Provisório, para evitar que o JPA tente interpretar isso daqui, pois na aula 317, sem isso, dá erro.
    @ManyToMany(mappedBy = "categories")
    // As anotações acima dão conta da relação muitos para muitos entre produtos e categorias.
    // Note que em mappedBy, o nome entre aspas é o nome da coleção lá na classe Product (linha 35).
    @JsonIgnore // Para evitar o loop infinito no postman
    private Set<Product> products = new HashSet<>();

    public Category() {

    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Product> getProducts() { return products; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
