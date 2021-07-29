package com.novelitech.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.novelitech.course.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    // Externamente ainda vai ser o OrderStatus, mas internamente vou gravar como um inteiro
    private Integer status;

    // Muitos pedidos para um cliente
    // A chave do relacionamento vai ficar na tabela 'orders'
    @ManyToOne
    @JoinColumn(name = "clientId")
    private User client;

    // Um order tem vários orderItem
    @OneToMany(mappedBy = "id.order") // Eu tenho o id na classe OrderItem, que por sua vez possui o order como atributo que faz parte da construção da chave
    private Set<OrderItem> items = new HashSet<>();

    // Mapeando as entidades para terem o mesmo id. Pedido com o id 5 e o Pagamento com o id 5
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {}

    public Order(Long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        setStatus(status);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return OrderStatus.valueOf(status);
    }

    public void setStatus(OrderStatus status) {
        if(status != null)
            this.status = status.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

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
