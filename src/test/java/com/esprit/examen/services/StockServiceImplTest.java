package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @InjectMocks
    StockServiceImpl stockService;

    @Mock
    StockRepository stockRepository;

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> expectedStocks = new ArrayList<>();
        Stock stock1 = new Stock(1L, "Produit 1", 10, 10);
        Stock stock2 = new Stock(2L, "Produit 2", 20, 20);
        expectedStocks.add(stock1);
        expectedStocks.add(stock2);

        when(stockRepository.findAll()).thenReturn(expectedStocks);

        List<Stock> retrievedStocks = stockService.retrieveAllStocks();

        assertEquals(expectedStocks, retrievedStocks);
    }

    @Test
    public void testAddStock() {
        Stock newStock = new Stock(null, "Produit 3", 30, 30);
        when(stockRepository.save(any(Stock.class))).thenReturn(newStock);

        Stock addedStock = stockService.addStock(newStock);

        assertNotNull(addedStock);
        assertEquals(newStock.getLibelleStock(), addedStock.getLibelleStock());
        assertEquals(newStock.getQte(), addedStock.getQte());
        assertEquals(newStock.getQteMin(), addedStock.getQteMin());
    }

    @Test
    public void testDeleteStock() {
        Long stockId = 1L;

        stockService.deleteStock(stockId);

        verify(stockRepository, times(1)).deleteById(stockId);
    }

    @Test
    public void testUpdateStock() {
        Stock updatedStock = new Stock(1L, "Produit 1 modifié", 15, 15);
        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);

        Stock returnedStock = stockService.updateStock(updatedStock);

        assertEquals(updatedStock.getIdStock(), returnedStock.getIdStock());
        assertEquals(updatedStock.getLibelleStock(), returnedStock.getLibelleStock());
        assertEquals(updatedStock.getQte(), returnedStock.getQte());
        assertEquals(updatedStock.getQteMin(), returnedStock.getQteMin());
    }

    @Test
    public void testRetrieveStock() {
        Long stockId = 1L;
        Stock expectedStock = new Stock(1L, "Produit 1", 10, 10);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertEquals(expectedStock, retrievedStock);
    }

    @Test
    public void testRetrieveStatusStock() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String msgDate = sdf.format(now);
        String finalMessage = "";
        String newLine = System.getProperty("line.separator");

        List<Stock> stocksEnRouge = new ArrayList<>();
        Stock stock1 = new Stock(1L, "Produit 1", 5, 10);
        stocksEnRouge.add(stock1);

        when(stockRepository.retrieveStatusStock()).thenReturn(stocksEnRouge);

        String retrievedMessage = stockService.retrieveStatusStock();

        finalMessage = newLine + finalMessage + msgDate + newLine + ": le stock ";
    }}