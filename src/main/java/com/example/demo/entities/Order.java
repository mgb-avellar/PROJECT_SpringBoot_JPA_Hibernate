package com.example.demo.entities;

import com.example.demo.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_order") // Esta anotação evita conflito de nomes de tabelas com palavras reservadas do SQL
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos de classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    /*
    As linha de código a seguir advêm da aula 315, quando da definição do ENUM
     */

    // private OrderStatus orderStatus; vamos comentar essa linha, pois queremos forçar o banco de dados a gravar um Integer
    private Integer orderStatus; // Isso levará a um erro no construtor:
                                 // precisamos converter esse Interger em OrderStatus (vide modificação no get e no set)
                                 // (vide modificação no construtor também)

    // Criando associações (um pedido tem um cliente (usuário) e um usuário tem vários pedidos) (Ver o planejamento no PDF)
    // Isso implica em criar uma lista de orders em User
    // Aqui, resolvemos a parte muitos para um; lá na classe User, resolvemos a parte um para muitos
    // Esta classe tem uma associação muitos para um com o meu usuário
    @ManyToOne
    @JoinColumn(name = "client_id") // Nome da chave estrangeira lá no banco de dados
    private User client;

    // Construtor

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        //this.orderStatus = orderStatus;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Instant getMoment() { return moment; }

    public void setMoment(Instant moment) { this.moment = moment; }

    public OrderStatus getOrderStatus() {
        // return orderStatus;
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        // this.orderStatus = orderStatus;
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() { return client; }

    public void setClient(User client) { this.client = client; }

    // HashCode e Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
