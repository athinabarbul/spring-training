package ro.msg.learning.shop.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dtos.StockDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvMessageConverterTest {

	@Test
	public void fromCsvTest() throws IOException {
		String csv = "locationId,productId,quantity" + "\n" + "9,1,23";
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());

		CsvMessageConverter.fromCsv(StockDto.class, inputStream).stream().peek(
				stockDto -> {
					assertEquals("locationId", 9, stockDto.getLocationId());
					assertEquals("productId", 1, stockDto.getProductId());
					assertEquals("quantity", 23, stockDto.getQuantity());
				}
		);
	}

	@Test
	public void toCsvTest() throws IOException {
		List<StockDto> stockDtoList = new ArrayList<>();
		stockDtoList.add(StockDto.builder()
				.productId(1)
				.locationId(9)
				.quantity(23)
				.build());

		OutputStream outputStream = new ByteArrayOutputStream();
		CsvMessageConverter.toCsv(StockDto.class, stockDtoList, outputStream);

		InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
		CsvMessageConverter.fromCsv(StockDto.class, inputStream).parallelStream().forEach(stockDto -> {
			assertEquals("locationId", 9, stockDto.getLocationId());
			assertEquals("productId", 1, stockDto.getProductId());
			assertEquals("quantity", 23, stockDto.getQuantity());
		});
	}
}
