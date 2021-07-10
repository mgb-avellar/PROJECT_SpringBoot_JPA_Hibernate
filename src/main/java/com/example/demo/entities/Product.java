package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos básicos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // Associações: um produtos tem muitas categorias (não faremos ainda a associação com Order : aula 317)
    // Usaremos o Set, não List, como nas classes feitas anteriormente para garantir que não teremos um produto
    //   com várias ocorrências da mesma categoria

    @Transient // Provisório, para evitar que o JPA tente interpretar isso daqui, pois na aula 317, sem isso, dá erro.
    private Set<Category> categories = new HashSet<>(); // Set é uma interface e por isso precisamos instanciar uma classe.
                                                        // Aliás, precisamos instanciar para que o conjunto não inicie nulo, mas sim vazio.

    public Product() {

    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public Set<Category> getCategories() { return categories; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
