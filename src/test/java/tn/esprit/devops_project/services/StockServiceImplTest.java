package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddStock() {
        Stock stockToAdd = new Stock(); // Create a sample stock object
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd); // Mock the save method of the repository

        Stock addedStock = stockService.addStock(stockToAdd);

        assertEquals(stockToAdd, addedStock); // Check if the returned stock is the same as the one added
        verify(stockRepository, times(1)).save(stockToAdd); // Verify that the save method of the repository is called once
    }

    @Test
    void testRetrieveStock() {
        Long id = 1L;
        Stock sampleStock = new Stock();
        when(stockRepository.findById(id)).thenReturn(java.util.Optional.of(sampleStock));

        Stock retrievedStock = stockService.retrieveStock(id);

        assertEquals(sampleStock, retrievedStock);
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void testRetrieveStock_NotFound() {
        Long id = 1L;
        when(stockRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(id));
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> sampleStockList = new ArrayList<>();
        sampleStockList.add(new Stock());
        sampleStockList.add(new Stock());
        when(stockRepository.findAll()).thenReturn(sampleStockList);

        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        assertEquals(sampleStockList.size(), retrievedStockList.size());
        assertEquals(sampleStockList, retrievedStockList);
        verify(stockRepository, times(1)).findAll();
    }
}