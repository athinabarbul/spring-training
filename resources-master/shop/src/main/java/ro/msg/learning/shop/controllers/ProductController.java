package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/all")
	public List<ProductDto> getAllProducts() {
		return productService.getAllProductsWithCategory();
	}

	@PostMapping("/new-product")
	public void addNewProduct(@RequestBody Product newProduct) {
		productService.addNewProduct(newProduct);
	}

	@PutMapping("/product/{productId}")
	public void updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
		productService.updateProductById(productId, product);
	}

	@DeleteMapping("/product/{productId}")
	public void deleteProduct(@PathVariable Integer productId) {
		productService.deleteExistingProduct(productId);
	}
}
