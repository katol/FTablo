package FTablo.repositories;

import FTablo.entities.Fight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FightsRepositoryTest {
    @Autowired
    FightsRepository fightsRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void savesFight() {
        Fight expectedFight = new Fight();
        expectedFight.setSecondsPassed(10);
        expectedFight.setEndTs(Timestamp.from(Instant.now()));
        expectedFight.setEndDescription("Some description");
        expectedFight.setRedName("Sveta");
        expectedFight.setRedScores(1);
        expectedFight.setBlueName("Tolian");
        expectedFight.setBlueScores(2);

        fightsRepository.save(expectedFight);

        Fight actualFight = fightsRepository.findAll().stream().findFirst().orElse(null);

        assertEquals(expectedFight, actualFight);
    }
}