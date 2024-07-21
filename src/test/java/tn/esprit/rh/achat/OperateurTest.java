package tn.esprit.rh.achat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

public class OperateurTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllOperateurs() {
        // Given
        List<Operateur> expectedOperateurs = new ArrayList<>();
        expectedOperateurs.add(new Operateur(1L, "John", "Doe", "password", null));
        expectedOperateurs.add(new Operateur(2L, "Jane", "Smith", "password", null));
        when(operateurRepository.findAll()).thenReturn(expectedOperateurs);

        // When
        List<Operateur> resultOperateurs = operateurService.retrieveAllOperateurs();

        // Then
        assertEquals(expectedOperateurs.size(), resultOperateurs.size());
        assertEquals(expectedOperateurs.get(0), resultOperateurs.get(0));
        assertEquals(expectedOperateurs.get(1), resultOperateurs.get(1));
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    public void testAddOperateur() {
        // Given
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur resultOperateur = operateurService.addOperateur(operateur);

        // Then
        assertEquals(operateur, resultOperateur);
        verify(operateurRepository, times(1)).save(operateur);
    }

    @Test
    public void testDeleteOperateur() {
        // Given
        Long operateurId = 1L;

        // When
        operateurService.deleteOperateur(operateurId);

        // Then
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }

    @Test
    public void testUpdateOperateur() {
        // Given
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur resultOperateur = operateurService.updateOperateur(operateur);

        // Then
        assertEquals(operateur, resultOperateur);
        verify(operateurRepository, times(1)).save(operateur);
    }

    @Test
    public void testRetrieveOperateur() {
        // Given
        Long operateurId = 1L;
        Operateur expectedOperateur = new Operateur(operateurId, "John", "Doe", "password", null);
        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(expectedOperateur));

        // When
        Operateur resultOperateur = operateurService.retrieveOperateur(operateurId);

        // Then
        assertEquals(expectedOperateur, resultOperateur);
        verify(operateurRepository, times(1)).findById(operateurId);
    }
}

