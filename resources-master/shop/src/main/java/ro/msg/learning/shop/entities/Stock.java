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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Product product;

    @Id
    @ManyToOne
    private Location location;
    private Integer quantity;
}
