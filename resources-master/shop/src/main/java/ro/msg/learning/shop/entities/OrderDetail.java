package ro.msg.learning.shop.entities;

import lombok.Data;
import ro.msg.learning.shop.composite.ids.OrderDetailId;

import javax.persistence.*;

@Entity
@Table
@Data
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Orders orders;

    @Id
    @ManyToOne
    private Product product;
    private Integer quantity;
}