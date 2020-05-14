package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Stock;

@Component
public class StockDtoMapper {
	public StockDto mapToStockDto(Stock stock) {
		return StockDto.builder()
				.quantity(stock.getQuantity())
				.productId(stock.getProductId())
				.locationId(stock.getLocationId())
				.build();
	}
}
