package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.composite.ids.OrderDetailId;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Id
    @Column(name = "orders_id")
    private Integer ordersId;

    @ManyToOne
    @JoinColumn(name = "orders_id", insertable = false, updatable = false)
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    private Location shippedFrom;

    private Integer quantity;
}