package com.example.demo.entities;

import com.example.demo.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();  // Aqui usamos o identificador da classe auxiliar

    private Integer quantity;
    private Double price;

    public OrderItem() {

    }

    public OrderItem(Order order, Product product, Integer quantity, Double price) { // 'Order order, Product product' colocados manualmente
        this.quantity = quantity;
        this.price = price;
        // Note que o id do tipo OrderItem PK não entra aqui; faremos manualmente, a seguir
        id.setOrder(order);
        id.setProduct(product);
    }

    // Não geramos automaticamente o Getter e o Setter para o id do tipo OrderItemPK
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Abaixo, o Getter e Setter, para o Order e o Product via id do tipo OrderItemPK

    @JsonIgnore // Para evitar o loop infinito do Json, pois no Java EE o que conta são od Getters
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    // Método subTotal, aula 323

    public Double getSubTotal() {
        // Lembrando que no Java EE, o que vale é o método get para que um resultado apareça no Json.
        // Por isso o getSubTotal e não simplesmente subTotal.
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
