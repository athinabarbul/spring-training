package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;

@Component
public class ProductMapper {

	public ProductDto mapProductAndCategoryToProductDto(Product product, ProductCategory productCategory) {
		return ProductDto.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.weight(product.getWeight())
				.category(productCategory != null ? productCategory.getName() : null)
				.categoryDescription(productCategory != null ? productCategory.getDescription() : null)
				.build();
	}
}
