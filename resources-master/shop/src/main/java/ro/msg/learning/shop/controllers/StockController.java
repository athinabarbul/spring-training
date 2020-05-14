package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.services.StockService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

	private final StockService stockService;

	@GetMapping("/export/{locationId}")
	public List<StockDto> exportStocks(@PathVariable Integer locationId) {
		return stockService.exportStockForLocation(locationId);
	}
}
