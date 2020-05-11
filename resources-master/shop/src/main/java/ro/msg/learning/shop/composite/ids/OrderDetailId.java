package ro.msg.learning.shop.composite.ids;

import lombok.Data;
import ro.msg.learning.shop.entities.Orders;
import ro.msg.learning.shop.entities.Product;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailId implements Serializable {
    private Orders orders;
    private Product product;

}
