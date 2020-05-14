package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.exceptions.FileTypeMismatchException;
import ro.msg.learning.shop.helpers.CsvMessageConverter;
import ro.msg.learning.shop.services.StockService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

	private final StockService stockService;

	@GetMapping(path = "/export/{locationId}", produces = "text/csv")
	public List<StockDto> getStocks(@PathVariable Integer locationId) {
		return stockService.exportStockForLocation(locationId);
	}

	@PostMapping("/from-csv")
	public List<StockDto> fromCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.getOriginalFilename().endsWith(".csv")) {
			throw new FileTypeMismatchException(file.getOriginalFilename() + " is not a CSV.");
		}
		return CsvMessageConverter.fromCsv(StockDto.class, file.getInputStream());
	}
}
