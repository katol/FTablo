package FTablo.services;

import FTablo.entities.Fight;
import FTablo.exceptions.NoSuchFightException;
import FTablo.repositories.FightsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FightsServiceTest {

    @Autowired
    private FightsService fightsService;

    @MockBean
    FightsRepository fightsRepository;

    private Fight fight;

    @BeforeEach
    void setUp() {
        fight = new Fight("Sveta", "Tolian");
        when(fightsRepository.findById(1)).thenReturn(Optional.of(fight));
    }

    @Test
    void createFight() {
        fightsService.createFight(fight);
        verify(fightsRepository, times(1)).save(any());
    }

    @Test
    void getFights() {
        fightsService.getFights();
        verify(fightsRepository, times(1)).findAll();
    }

    @Test
    void getFight() {
        fightsService.getFight(1);
        verify(fightsRepository, times(1)).findById(1);
    }

    @Test
    void getFight_throwsException_whenFightDoesNotExist() {
        assertThrows(NoSuchFightException.class, () -> fightsService.getFight(2));
    }
}