package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.composite.ids.StockId;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, StockId> {
	@Query(value = "select * from Stock s where s.location_id = ?1", nativeQuery = true)
	List<Stock> getStocksForLocation(Integer locationId);

	@Query(value = "select s.location_id from Stock s where s.quantity >= ?1 and s.product_id = ?2", nativeQuery = true)
	List<Integer> getLocationsForProduct(Integer quantity, Integer productId);

	@Query(value = "select s.location_id from Stock s where s.product_id = ?1 and s.quantity>= ?2 order by s.quantity desc limit 1", nativeQuery = true)
	Optional<Integer> getMostAbundantStockForProduct(Integer productId, Integer quantity);
}
