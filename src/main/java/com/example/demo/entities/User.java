package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // Basic attributes
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Associations (instantiate collections)
    // (ainda não, pois essa é a primeira que estamos criando no momento da redação deste código)

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
