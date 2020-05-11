package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Override
	List<Product> findAllById(Iterable<Integer> iterable);

	@Override
	List<Product> findAll();

	@Override
	<S extends Product> S save(S s);

	@Override
	void delete(Product product);
}
