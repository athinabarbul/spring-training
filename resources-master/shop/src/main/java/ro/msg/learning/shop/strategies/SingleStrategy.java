package ro.msg.learning.shop.strategies;

import org.hibernate.boot.registry.selector.spi.StrategySelectionException;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SingleStrategy implements Strategy {

	@Override
	public List<OrderDetail> algorithmForLocation(List<OrderDetail> orderDetailList, StockRepository stockRepository,
	                                              LocationRepository locationRepository) {
		List<Integer> locationsList = orderDetailList.stream().map(orderDetail ->
				stockRepository.getLocationsForProduct(orderDetail.getQuantity(),
						orderDetail.getProduct().getId()))
				.flatMap(List::stream)
				.collect(Collectors.toList());

		Optional<Location> mostCommonLocation = locationRepository.findById(mostCommonLocation(locationsList, orderDetailList.size()));
		if (!mostCommonLocation.isPresent()) {
			throw new StrategySelectionException("Single Strategy cannot be applied. No location was found in the database");
		}

		return orderDetailList.stream().map(orderDetail -> {
			orderDetail.setShippedFrom(mostCommonLocation.get());
			return orderDetail;
		}).collect(Collectors.toList());
	}

	public static <T> T mostCommonLocation(List<T> list, Integer productsSize) {
		Map<T, Integer> map = new HashMap<>();
		for (T t : list) {
			Integer val = map.get(t);
			map.put(t, val == null ? 1 : val + 1);
		}

		Map.Entry<T, Integer> max = null;
		for (Map.Entry<T, Integer> e : map.entrySet()) {
			if (max == null || e.getValue() > max.getValue())
				max = e;
		}

		if (max == null || !max.getValue().equals(productsSize)) {
			throw new StrategySelectionException("Single Strategy cannot be applied. No common locations for products");
		}
		return max.getKey();
	}
}
