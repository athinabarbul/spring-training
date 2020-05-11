package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;
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

	public void addNewProduct(Product newProduct) {
		 productRepository.save(newProduct);
	}

	public void updateProductById(Integer productId, Product product) {
		Optional<Product> updateProduct = productRepository.findById(productId);
		if (!updateProduct.isPresent()) {
			throw new EntityNotFoundException();
		} else {
			if (product.getName() != null) {
				updateProduct.get().setName(product.getName());
			}
			if (product.getDescription() != null) {
				updateProduct.get().setDescription(product.getDescription());
			}
			if (product.getImageUrl() != null) {
				updateProduct.get().setImageUrl(product.getImageUrl());
			}
			if (product.getPrice() != null) {
				updateProduct.get().setPrice(product.getPrice());
			}
			if (product.getWeight() != null) {
				updateProduct.get().setWeight(product.getWeight());
			}
			if (product.getCategory() != null) {
				updateProduct.get().setCategory(product.getCategory());
			}
			if (product.getSupplier() != null) {
				updateProduct.get().setSupplier(product.getSupplier());
			}
			 productRepository.save(updateProduct.get());
		}
	}
}
