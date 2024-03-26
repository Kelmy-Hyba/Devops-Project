package com.esprit.examen.services;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReglementServiceImplTest {

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;

    @Mock
    private FactureRepository factureRepository;

    @Test
    public void retrieveAllReglements() {
        List<Reglement> expectedReglements = new ArrayList<>();
        expectedReglements.add(new Reglement());
        expectedReglements.add(new Reglement());

        when(reglementRepository.findAll()).thenReturn(expectedReglements);

        List<Reglement> retrievedReglements = reglementService.retrieveAllReglements();

        assertEquals(expectedReglements, retrievedReglements);
    }

    @Test
    public void addReglement() {
        Reglement newReglement = new Reglement();

        when(reglementRepository.save(newReglement)).thenReturn(newReglement);

        Reglement savedReglement = reglementService.addReglement(newReglement);

        assertEquals(newReglement, savedReglement);
        verify(reglementRepository).save(newReglement);
    }

    @Test
    public void retrieveReglementById() {
        Long id = 1L;
        Reglement expectedReglement = new Reglement();

        when(reglementRepository.findById(id)).thenReturn(java.util.Optional.of(expectedReglement));

        Reglement retrievedReglement = reglementService.retrieveReglement(id);

        assertNotNull(retrievedReglement);
        assertEquals(expectedReglement, retrievedReglement);
    }

    @Test
    public void testRetrieveReglementByFactureWithJpql() {
        Long idFacture = 1L;
        List<Reglement> expectedReglements = new ArrayList<>();
        expectedReglements.add(new Reglement());

        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(expectedReglements);

        List<Reglement> retrievedReglements = reglementService.retrieveReglementByFacture(idFacture);

        assertEquals(expectedReglements, retrievedReglements);
        verify(reglementRepository).retrieveReglementByFacture(idFacture);
    }

    @Test
    public void getChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedChiffreAffaire = 1000.0f;

        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        float retrievedChiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        assertEquals(expectedChiffreAffaire, retrievedChiffreAffaire, 0.001f);
        verify(reglementRepository).getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}