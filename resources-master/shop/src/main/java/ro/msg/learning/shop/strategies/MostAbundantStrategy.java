package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MostAbundantStrategy implements Strategy {
	@Override
	public List<OrderDetail> algorithmForLocation(List<OrderDetail> orderDetailList, StockRepository stockRepository,
	                                              LocationRepository locationRepository) {
		return orderDetailList.stream().map(orderDetail -> {
			orderDetail.setShippedFrom(
					locationRepository.findById(
							stockRepository.getMostAbundantStockForProduct(orderDetail.getProduct().getId()).get()).get());
			return orderDetail;
		}).collect(Collectors.toList());

	}
}
