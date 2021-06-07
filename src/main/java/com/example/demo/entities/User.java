package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    Esta observação faz parte da terceira aula (310), após a criação desta entidade (User)
        e seu Resource.
    É preciso inserir algumas annotations do JPA para instruí-lo a como converter
        os objetos para o modelo relacional.

    @Entity : instrui o JPA que a classe User é uma entidade;
    @Id : instrui o JPA qual é a chave primária da tabela do banco de dados (no nosso caso, o ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY) : instrui o JPA que a chave é autoincrementável
        no banco de dados.
 */

@Entity
@Table(name = "tb_user") // Esta anotação evita conflito de nomes de tabelas com palavras reservadas do SQL
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // Basic attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Associations (instantiate collections)
    // (ainda não, pois essa é a primeira que estamos criando no momento da redação deste código)
    // Feita na aula 314

    @JsonIgnore  // Evita um loop infinito de User chamar Order que chama User etc. Eu poderia colocar isso também em Order.
    @OneToMany(mappedBy = "client") // Aqui resolvo a associação um para muitos (Ver comentários em Order)
    private List<Order> orders = new ArrayList<>(); // Instancio porque é uma coleção; também preciso de um getter


    // Constructor (precisamos de um vazio também, pois estamos usando framework)
    public User () {

    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters and Setters (collections only getters)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Order> getOrders() { return orders; }

    // HashCodes and Equals
    // Aqui colocamos os atributos que quisermos para comparar dois objetos
    // Por padrão, colocaremos apenas o ID, mas podemos colocar outros mais adiante se necessário

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Serializable
    /*
        Com essa classe, os objetos podem ser transformados em cadeias de bytes.
            Com isso o objeto pode trafegar na rede, ou ser gravado em arquivos, etc.

        Na definição precisamos mudar a classe para 'public class User implements Serializable'
            e criar o 'private static final long serialVersionUID = 1L;' (ver acima)
     */

    // Resources
    /*
        Para ver se o REST da aplicação Spring Boot está funcionando corretamente,
            criamos o 'Resource' correspondente à classe User (no pacote resources).
     */

}
