package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Test
    void addProduct() {
        // Given
        StockRepository stockRepository = mock(StockRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);

        // Create a mock Stock object
        Stock mockStock = new Stock();
        mockStock.setIdStock(1); // Set necessary properties

        // Set up the behavior of the stockRepository mock to return the mockStock object
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(mockStock));

        // Create a Product object
        Product productToAdd = new Product();
        productToAdd.setTitle("test");

        // When
        productService.addProduct(productToAdd, mockStock.getIdStock());

        // Then
        verify(productRepository, times(1)).save(productToAdd);
    }


    @Test
    void retrieveProduct() {
        // Implement your test for retrieving a product
    }

    @Test
    void retreiveAllProduct() {
        // Implement your test for retrieving all products
    }

    @Test
    void retrieveProductByCategory() {
        // Implement your test for retrieving products by category
    }

    @Test
    void deleteProduct() {
        // Implement your test for deleting a product
    }

    @Test
    void retrieveProductStock() {
        // Implement your test for retrieving product stock
    }
}