package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.mapper.StockDtoMapper;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
	private final StockRepository stockRepository;
	private final StockDtoMapper stockDtoMapper;

	public List<StockDto> exportStockForLocation(Integer locationId) {
		return this.stockRepository.getStocksForLocation(locationId).stream()
				.map(stockDtoMapper::mapToStockDto).collect(Collectors.toList());
	}
}
