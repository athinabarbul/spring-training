package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
