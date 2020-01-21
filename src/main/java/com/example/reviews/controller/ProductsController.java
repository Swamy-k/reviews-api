package com.example.reviews.controller;

import com.example.reviews.controller.dto.ProductDto;
import com.example.reviews.entity.mysql.Product;
import com.example.reviews.service.ProductService;
import com.example.reviews.service.ReviewService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized. "),
        @ApiResponse(code = 500, message = "The server is down. Please make sure that the  reviews api is running."),
        @ApiResponse(code = 404, message = "Not Found if products not found"),
})
public class ProductsController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final ModelMapper mapper;

    public ProductsController(ProductService productService, ReviewService reviewService, ModelMapper mapper) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

    /**
     * Creates a product.
     * Accept product as argument and Saves the product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        productService.persist(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        return optionalProduct
                .map(product -> {
                    ProductDto productDto = mapper.map(product, ProductDto.class);
                    productDto.setScore(reviewService.getAverageReviewScore(id));
                    return new ResponseEntity<>(productDto, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Lists all products.
     *
     * @return The list of products if found, or a 404 not found.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<Product> productList = productService.findAllProducts();

        if (productList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type listType = new TypeToken<List<ProductDto>>() {}.getType();
        List<ProductDto> products = mapper.map(productList, listType);
        products = products.stream().peek(product ->
                product.setScore(reviewService.getAverageReviewScore(product.getId()))).collect(Collectors.toList());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
