package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.List;

public interface Strategy {
	List<OrderDetail> algorithmForLocation(List<OrderDetail> orderDetailList, StockRepository stockRepository,
	                                       LocationRepository locationRepository);
}
