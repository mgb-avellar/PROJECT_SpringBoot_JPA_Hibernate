package com.example.demo.entities.pk;

import com.example.demo.entities.Order;
import com.example.demo.entities.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // Essa anotação do JPA nos diz que esta é uma classe é auxiliar
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    Esta é uma classe auxiliar da classe OrderItem para atuar como chave composta. Isso porque a
    classe OrderItem tem associação com a classe Product e com a classe Order, mas não tem um identificador
    próprio (chave primária composta em SQL): ela é identificada pelas duas outras classes, a Product e a Order.
     */

    // Essa classe não possui construtores.

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    /*
    Nos HashCodes e Equals anteriores, podíamos escolher apenas um parâmetro se quiséssemos (escolhíamos o ID);
    agora, porque essa classe precisa ser associada e comparada por duas classes, precisamos incluir os dois parâmetros aqui,
    'order' e 'product'.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return order.equals(that.order) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
