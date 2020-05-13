package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrdersDto;
import ro.msg.learning.shop.entities.Orders;

@Component
public class OrderMapper {
	public Orders mapToOrders(OrdersDto ordersDto) {
		return Orders.builder()
				.createdAt(ordersDto.getCreatedAt())
				.addressCountry(ordersDto.getAddressCountry())
				.addressCounty(ordersDto.getAddressCounty())
				.addressCity(ordersDto.getAddressCity())
				.addressStreet(ordersDto.getAddressStreet())
				.build();
	}


}
