package ro.msg.learning.shop.entities;

import lombok.Data;
import ro.msg.learning.shop.composite.ids.StockId;

import javax.persistence.*;

@Entity
@Data
@Table
@IdClass(StockId.class)
public class Stock {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Id
    @Column(name = "location_id")
    private Integer locationId;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;

    private Integer quantity;
}
