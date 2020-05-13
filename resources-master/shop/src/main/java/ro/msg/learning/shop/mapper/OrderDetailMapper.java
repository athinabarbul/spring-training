package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Orders;
import ro.msg.learning.shop.repositories.OrdersRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

@Component
public class OrderDetailMapper {
	public OrderDetail mapToOrderDetail(OrderDetailDto orderDetailDto, Orders order,
	                                    OrdersRepository ordersRepository,
	                                    ProductRepository productRepository) {
		return OrderDetail.builder()
				.ordersId(order.getId())
				.orders(ordersRepository.findById(order.getId()).isPresent() ?
						ordersRepository.findById(order.getId()).get() : null)
				.productId(orderDetailDto.getProductId())
				.quantity(orderDetailDto.getQuantity())
				.product(productRepository.findById(orderDetailDto.getProductId()).isPresent() ?
						productRepository.findById(orderDetailDto.getProductId()).get() : null)
				.build();
	}

}
