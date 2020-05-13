package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.composite.ids.StockId;
import ro.msg.learning.shop.dtos.OrdersDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.strategies.Strategy;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
	private final OrdersRepository ordersRepository;
	private final StockRepository stockRepository;
	private final LocationRepository locationRepository;
	private final ProductRepository productRepository;
	private final OrderDetailMapper orderDetailMapper;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderMapper orderMapper;
	private final Strategy strategy;

	public Orders addNewOrder(OrdersDto ordersDto) {
		Orders order = this.orderMapper.mapToOrders(ordersDto);
		this.ordersRepository.save(order);

		order.setOrderDetailList(ordersDto.getOrderDetailDtos().stream().map(
				orderDetailDto -> orderDetailMapper.mapToOrderDetail(orderDetailDto, order,
						ordersRepository,
						productRepository))
				.collect(Collectors.toList()));

		List<OrderDetail> orderDetailList = strategy.algorithmForLocation(order.getOrderDetailList(), stockRepository,
				locationRepository);

		orderDetailList.forEach(orderDetail ->
				decreaseStock(orderDetail.getProduct(), orderDetail.getShippedFrom(), orderDetail.getQuantity()));

		this.orderDetailRepository.saveAll(orderDetailList);
		this.ordersRepository.save(order);
		return order;
	}

	public void decreaseStock(Product product, Location location, Integer quantity) {
		Optional<Stock> stock = this.stockRepository.findById(StockId.builder()
				.productId(product.getId())
				.locationId(location.getId())
				.build());
		if (!stock.isPresent()) {
			throw new EntityNotFoundException();
		} else {
			stock.get().setQuantity(stock.get().getQuantity() - quantity);
			this.stockRepository.save(stock.get());
		}
	}
}