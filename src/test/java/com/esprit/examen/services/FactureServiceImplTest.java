package com.esprit.examen.services;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FactureServiceImplTest {

    @InjectMocks
    private FactureServiceImpl factureService;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    @Mock
    private DetailFactureRepository detailFactureRepository;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private ReglementServiceImpl reglementService;


    @Test
    public void retrieveAllFactures() {
        List<Facture> expectedFactures = new ArrayList<>();
        expectedFactures.add(new Facture());
        expectedFactures.add(new Facture());

        when(factureRepository.findAll()).thenReturn(expectedFactures);

        List<Facture> retrievedFactures = factureService.retrieveAllFactures();

        assertEquals(expectedFactures, retrievedFactures);
    }

    @Test
    public void addFacture() {
        Facture newFacture = new Facture();

        when(factureRepository.save(newFacture)).thenReturn(newFacture);

        Facture savedFacture = factureService.addFacture(newFacture);

        assertEquals(newFacture, savedFacture);
        verify(factureRepository).save(newFacture);
    }



    @Test
    public void testAddDetailsFactureWithNonExistentProduct() {
        Facture facture = new Facture();
        Set<DetailFacture> detailsFacture = new HashSet<>();
        DetailFacture detail = new DetailFacture();
        detail.setProduit(new Produit());
        detailsFacture.add(detail);

        when(produitRepository.findById(detail.getProduit().getIdProduit())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> factureService.addDetailsFacture(facture, detailsFacture));

        verify(produitRepository).findById(detail.getProduit().getIdProduit());
        verify(detailFactureRepository, never()).save(detail);
    }

    @Test
    public void cancelFacture() {
        // Set up data for the test
        Long factureId = 1L;

        // Call the method to be tested
        factureService.cancelFacture(factureId);

        // Verify expected behavior
        // Choose appropriate verifications for method being tested
        // E.g., for this method:
        verify(factureRepository).updateFacture(factureId);
        // Or verify(factureRepository).findById(factureId); and verify(factureRepository).save(facture); if applicable
    }

    @Test
    public void retrieveFacture() {
        // Set up data for the test
        Long factureId = 1L;
        Facture expectedFacture = new Facture();
        expectedFacture.setIdFacture(factureId);

        // Configure mocks for expected behavior
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(expectedFacture));

        // Call the method to be tested
        Facture retrievedFacture = factureService.retrieveFacture(factureId);

        // Assert expected results
        assertEquals(expectedFacture, retrievedFacture);
        verify(factureRepository).findById(factureId);
    }


}