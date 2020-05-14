package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.composite.ids.StockId;

import javax.persistence.*;

@Entity
@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StockId.class)
public class Stock {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Id
    @Column(name = "location_id")
    private Integer locationId;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;

    private Integer quantity;
}
