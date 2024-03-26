package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperateurServiceImplTest {
    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;

    // Test for retrieveAllOperateurs
    @Test
    public void testRetrieveAllOperateurs() {
        List<Operateur> expectedOperateurs = new ArrayList<>();
        expectedOperateurs.add(new Operateur());
        expectedOperateurs.add(new Operateur());

        when(operateurRepository.findAll()).thenReturn(expectedOperateurs);

        List<Operateur> retrievedOperateurs = operateurService.retrieveAllOperateurs();

        assertEquals(expectedOperateurs, retrievedOperateurs);
    }

    // Test for addOperateur
    @Test
    public void testAddOperateur() {
        Operateur operateur = new Operateur();

        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur savedOperateur = operateurService.addOperateur(operateur);

        assertEquals(operateur, savedOperateur);
        verify(operateurRepository).save(operateur);
    }

    // Test for deleteOperateur
    @Test
    public void testDeleteOperateur() {
        Long operateurId = 1L;

        operateurService.deleteOperateur(operateurId);

        verify(operateurRepository).deleteById(operateurId);
    }

    // Test for updateOperateur
    @Test
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);

        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur updatedOperateur = operateurService.updateOperateur(operateur);

        assertEquals(operateur, updatedOperateur);
        verify(operateurRepository).save(operateur);
    }

    // Test for retrieveOperateur with existing operateur
    @Test
    public void testRetrieveOperateurWithExistingOperateur() {
        Long operateurId = 1L;
        Operateur expectedOperateur = new Operateur();
        expectedOperateur.setIdOperateur(operateurId);

        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(expectedOperateur));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(operateurId);

        assertEquals(expectedOperateur, retrievedOperateur);
        verify(operateurRepository).findById(operateurId);
    }

    // Test for retrieveOperateur with non-existent operateur
    @Test
    public void testRetrieveOperateurWithNonExistentOperateur() {
        Long operateurId = 1L;

        when(operateurRepository.findById(operateurId)).thenReturn(Optional.empty());

        Operateur retrievedOperateur = operateurService.retrieveOperateur(operateurId);

        assertNull(retrievedOperateur);
        verify(operateurRepository).findById(operateurId);
    }

}