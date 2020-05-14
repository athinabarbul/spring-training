package ro.msg.learning.shop.strategies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.StrategyException;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MostAbundantStrategyTest {
	@Mock
	private StockRepository stockRepository;
	@Mock
	private LocationRepository locationRepository;

	private OrderDetail orderDetail;
	private MostAbundantStrategy mostAbundantStrategy;

	@Before
	public void init() {
		Product productOne = Product.builder()
				.id(1)
				.build();

		orderDetail = OrderDetail.builder()
				.productId(productOne.getId())
				.product(productOne)
				.quantity(7)
				.build();

		Location location = Location.builder()
				.id(8)
				.build();

		Stock stock = Stock.builder()
				.locationId(location.getId())
				.location(location)
				.product(productOne)
				.quantity(50)
				.productId(productOne.getId())
				.build();

		location.setStockList(Collections.singletonList(stock));

		when(locationRepository.findById(any())).thenReturn(Optional.of(location));
		when(stockRepository.getLocationsForProduct(eq(7), anyInt())).thenReturn(Collections.singletonList(location.getId()));
		when(stockRepository.getLocationsForProduct(eq(85), anyInt())).thenThrow(StrategyException.class);

		mostAbundantStrategy = new MostAbundantStrategy();
	}

	@Test
	public void getStockForProduct() {
		try {
			mostAbundantStrategy.algorithmForLocation(Collections.singletonList(orderDetail), stockRepository, locationRepository);
		} catch (Exception e) {
			fail("Exception  thrown");
		}
	}

	@Test(expected = StrategyException.class)
	public void getStockForProductFail() {
		orderDetail.setQuantity(85);
		mostAbundantStrategy.algorithmForLocation(Collections.singletonList(orderDetail), stockRepository, locationRepository);
	}
}
