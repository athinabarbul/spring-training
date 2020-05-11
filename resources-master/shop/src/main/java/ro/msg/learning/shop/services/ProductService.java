package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductCategoryRepository categoryRepository;
	private final ProductMapper productMapper;

	public List<ProductDto> getAllProductsWithCategory() {
		List<Product> allProducts = this.productRepository.findAll();

		return allProducts.stream().map(product -> {
			Optional<ProductCategory> category = categoryRepository.findById(product.getCategory().getId());
			return productMapper.mapProductAndCategoryToProductDto(product, category.isPresent() ? category.get() : null);
		}).collect(Collectors.toList());
	}

}
