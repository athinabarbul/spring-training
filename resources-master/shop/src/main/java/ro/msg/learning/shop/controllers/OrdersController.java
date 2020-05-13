package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrdersDto;
import ro.msg.learning.shop.entities.Orders;
import ro.msg.learning.shop.services.OrdersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
	private final OrdersService ordersService;

	@PostMapping("/new-order")
	public Orders addNewOrder(@RequestBody OrdersDto newOrderDto) {
		return ordersService.addNewOrder(newOrderDto);
	}
}
