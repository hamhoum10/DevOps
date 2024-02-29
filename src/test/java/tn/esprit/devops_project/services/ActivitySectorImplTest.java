package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivitySectorImplTest {

    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    private ActivitySectorImpl activitySectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllActivitySectors() {
        List<ActivitySector> activitySectors = new ArrayList<>();
        // Add some mock data to the list
        activitySectors.add(new ActivitySector(1L, "Code1", "Sector 1", null));
        activitySectors.add(new ActivitySector(2L, "Code2", "Sector 2", null));

        when(activitySectorRepository.findAll()).thenReturn(activitySectors);

        List<ActivitySector> result = activitySectorService.retrieveAllActivitySectors();

        assertEquals(2, result.size());
        // You can add more assertions based on your specific requirements
    }

    @Test
    void addActivitySector() {
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(1L);
        activitySector.setCodeSecteurActivite("NewCode");
        activitySector.setLibelleSecteurActivite("New Sector");

        when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);

        ActivitySector result = activitySectorService.addActivitySector(activitySector);

        assertEquals(activitySector, result);
    }

    @Test
    void deleteActivitySector() {
        Long idToDelete = 1L;

        // Perform the delete operation
        activitySectorService.deleteActivitySector(idToDelete);

        // Verify that the deleteById method was called with the correct ID
        verify(activitySectorRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void updateActivitySector() {
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(1L);
        activitySector.setCodeSecteurActivite("UpdatedCode");
        activitySector.setLibelleSecteurActivite("Updated Sector");

        when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);

        ActivitySector result = activitySectorService.updateActivitySector(activitySector);

        assertEquals(activitySector, result);
    }

    @Test
    void retrieveActivitySector() {
        Long idToRetrieve = 1L;
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(idToRetrieve);
        activitySector.setCodeSecteurActivite("Code1");
        activitySector.setLibelleSecteurActivite("Sector 1");

        when(activitySectorRepository.findById(idToRetrieve)).thenReturn(Optional.of(activitySector));

        ActivitySector result = activitySectorService.retrieveActivitySector(idToRetrieve);

        assertEquals(activitySector, result);
    }
}