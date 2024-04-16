package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
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
        // Given
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, null);

        // Create a mock Product object
        Product mockProduct = new Product();
        mockProduct.setIdProduct(1L); // Set necessary properties

        // Set up the behavior of the productRepository mock to return the mockProduct object
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));

        // When
        Product retrievedProduct = productService.retrieveProduct(1L);

        // Then
        assertNotNull(retrievedProduct);
        assertEquals(mockProduct.getIdProduct(), retrievedProduct.getIdProduct());
    }

    @Test
    void retrieveAllProduct() {
        // Given
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, null);

        // Create a list of mock Product objects
        List<Product> mockProducts = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Product mockProduct = new Product();
            mockProduct.setIdProduct((long) i); // Set necessary properties
            mockProducts.add(mockProduct);
        }

        // Set up the behavior of the productRepository mock to return the list of mockProduct objects
        when(productRepository.findAll()).thenReturn(mockProducts);

        // When
        List<Product> retrievedProducts = productService.retreiveAllProduct();

        // Then
        assertNotNull(retrievedProducts);
        assertEquals(mockProducts.size(), retrievedProducts.size());
        for (int i = 0; i < mockProducts.size(); i++) {
            assertEquals(mockProducts.get(i).getIdProduct(), retrievedProducts.get(i).getIdProduct());
        }
    }

    @Test
    void retrieveProductByCategory() {
        // Given
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, null);

        // Create a mock Product object
        Product mockProduct = new Product();
        mockProduct.setIdProduct(1L); // Set necessary properties

        // Set up the behavior of the productRepository mock to return the mockProduct object
        when(productRepository.findByCategory(any(ProductCategory.class))).thenReturn(List.of(mockProduct));

        // When
        List<Product> retrievedProducts = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        // Then
        assertNotNull(retrievedProducts);
        assertEquals(1, retrievedProducts.size());
        assertEquals(mockProduct.getIdProduct(), retrievedProducts.get(0).getIdProduct());
    }

    @Test
    void deleteProduct() {
        // Given
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, null);

        // When
        productService.deleteProduct(1L);

        // Then
        verify(productRepository, times(1)).deleteById(1L);
    }


    @Test
    void retrieveProductStock() {
        // Given
        StockRepository stockRepository = mock(StockRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);

        // Create a mock Stock object
        Stock mockStock = new Stock();
        mockStock.setIdStock(1); // Set necessary properties

        // Set up the behavior of the stockRepository mock to return the mockStock object
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(mockStock));

        // Create a mock list of products
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product());
        mockProducts.add(new Product());
        // Set up the behavior of the productRepository mock to return the mockProducts list
        when(productRepository.findByStockIdStock(anyLong())).thenReturn(mockProducts);

        // When
        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        // Then
        assertNotNull(retrievedProducts);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(mockProducts.size(), retrievedProducts.size());
        assertEquals(mockProducts.get(0), retrievedProducts.get(0));
        assertEquals(mockProducts.get(1), retrievedProducts.get(1));

        // Verify that findByStockIdStock method of ProductRepository is called with correct argument
        verify(productRepository).findByStockIdStock(1L);
    }

}