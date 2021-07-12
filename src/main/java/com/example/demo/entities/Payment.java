package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    /*
    Da aula 322, depreende-se que existe uma associação entre Order e Payment, do tipo um para um.
    No entanto, a classe Order é o que chamamos de classe independente, pois ela sempre existe em
    um único estado, enquanto Payment é a classe dependente, pois pode ter dois estados: pago e não pago,
    ou 0 e 1 como no planejamento.
    Para as anotações do JPA, nesse caso um para um, algumas coisas diferem quando lidamos com classes
    dependentes e independentes. Note as diferenças das anotações desta classe (dependente) para as
    de Order (independente).
     */
    @OneToOne
    @MapsId
    private Order order;

    public Payment() {

    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Instant getMoment() { return moment; }

    public void setMoment(Instant moment) { this.moment = moment; }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
