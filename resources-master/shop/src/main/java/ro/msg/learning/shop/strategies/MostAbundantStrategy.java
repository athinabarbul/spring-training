package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.exceptions.StrategyException;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MostAbundantStrategy implements Strategy {
	@Override
	public List<OrderDetail> algorithmForLocation(List<OrderDetail> orderDetailList, StockRepository stockRepository,
	                                              LocationRepository locationRepository) {
		return orderDetailList.stream().map(orderDetail -> {
			orderDetail.setShippedFrom(
					locationRepository.findById(
							getLocationId(stockRepository, orderDetail)).get());
			return orderDetail;
		}).collect(Collectors.toList());

	}

	private Integer getLocationId(StockRepository stockRepository, OrderDetail orderDetail) {
		Optional<Integer> locationId = stockRepository
				.getMostAbundantStockForProduct(orderDetail.getProduct().getId(), orderDetail.getQuantity());
		if (locationId.isPresent()) {
			return locationId.get();
		} else {
			throw new StrategyException("Most Abundant Strategy cannot be applied here!");
		}
	}
}
