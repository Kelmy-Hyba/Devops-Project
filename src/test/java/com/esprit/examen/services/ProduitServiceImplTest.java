package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProduitServiceImplTest {

    @InjectMocks
    ProduitServiceImpl produitService;

    @Mock
    ProduitRepository produitRepository;

    /*
    @Test
    public void assignProduitToStockShouldAssignTheProduitToTheStock() {
        Produit produit = new Produit(1L, "produit1", 10.0, new Stock(1L, "stock1", 10, 5));
        Stock stock = new Stock(1L, "stock1", 10, 5);
        when(produitRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(produit));
        produitService.assignProduitToStock(1L, 1L);
        assertEquals(produit.getStock(), stock);
    }

     */




    /**
     * should delete the product with the given id
     */
 /*
    @Test
    public void deleteProduitWithValidId() {
        Produit produit =
                new Produit(1L, "code", "libelle", 10, new Date(), new Date(), null, null, null);
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        produitService.deleteProduit(1L);
        verify(produitRepository, times(1)).deleteById(1L);
    }
*/
    @Test
    public void retrieveProduitWhenProduitIdIsFoundThenReturnTheProduit() {
        Produit produit = new Produit(1L, "produit1", 10.0, new Stock(1L, "stock1", 10, 5));
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        Produit produitFound = produitService.retrieveProduit(1L);
        assertEquals(produit, produitFound);
    }

    @Test
    public void updateProduitWhenIdIsValid() {
        Produit produit = new Produit(1L, "produit1", 10.0, new Stock(1L, "stock1", 10, 5));
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit produit1 = produitService.updateProduit(produit);
        assertEquals(produit, produit1);
    }

    @Test
    public void addProduitWhenProduitIsNotNull() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("code");
        produit.setLibelleProduit("libelle");
        produit.setPrix(10);
        produit.setDateCreation(new Date());
        produit.setDateDerniereModification(new Date());
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitService.addProduit(produit);
        assertNotNull(produit);
    }

    @Test
    public void testRetrieveAllProduits() {
        List<Produit> produits = new ArrayList<>();
        Stock stock = new Stock(1L, "Produit", 10, 10);
        produits.add(new Produit(1L, "Produit 1", 10.0, new Stock()));
        produits.add(new Produit(2L, "Produit 2", 20.0, new Stock()));
        when(produitRepository.findAll()).thenReturn(produits);

        List<Produit> retrievedProduits = produitService.retrieveAllProduits();

        assertEquals(2, retrievedProduits.size());
    }

    @Test
    void deleteProduit() {

    }

    @Test
    void shouldUpdateProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("code");
        produit.setLibelleProduit("libelle");
        produit.setPrix(10);
        produit.setDateCreation(new Date());
        produit.setDateDerniereModification(new Date());
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitService.addProduit(produit);
        assertNotNull(produit);

    }

    @Test
    void assignProduitToStock() {
    }
}