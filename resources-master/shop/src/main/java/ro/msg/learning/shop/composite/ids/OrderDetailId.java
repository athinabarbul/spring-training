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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
