package ro.msg.learning.shop.composite.ids;

import lombok.Data;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Product;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class StockId implements Serializable {
    private Product product;
    private Location location;
}
