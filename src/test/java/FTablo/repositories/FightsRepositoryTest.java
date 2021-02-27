package FTablo.repositories;

import FTablo.entities.Fight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.flywaydb.core.Flyway;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FightsRepositoryTest {
    @Autowired
    private Flyway flyway;

    @Autowired
    private FightsRepository fightsRepository;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void savesFight() {
        Fight expectedFight = new Fight();
        expectedFight.setSecondsPassed(10);
        expectedFight.setLastExchangeNumber(1);
        expectedFight.setLastTs(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS)));
        expectedFight.setLastDescription("Some description");
        expectedFight.setRedName("Sveta");
        expectedFight.setRedScores(1);
        expectedFight.setRedPenalties(0);
        expectedFight.setRedVideoReplays(1);
        expectedFight.setBlueName("Tolian");
        expectedFight.setBlueScores(2);
        expectedFight.setBluePenalties(1);
        expectedFight.setBlueVideoReplays(0);

        fightsRepository.save(expectedFight);

        Fight actualFight = fightsRepository.findAll().stream().findFirst().orElse(null);
        assertEquals(expectedFight, actualFight);
    }

    @Test
    void savesFightsWithNamesOnly() {
        fightsRepository.save(new Fight("Sveta", "Tolian"));

        Fight actualFight = fightsRepository.findAll().stream().findFirst().orElse(null);

        if (actualFight == null) {
            fail();
        }

        assertEquals(0, actualFight.getSecondsPassed());
        assertEquals(0, actualFight.getLastExchangeNumber());
        assertEquals("Sveta", actualFight.getRedName());
        assertEquals(0, actualFight.getRedScores());
        assertEquals(0, actualFight.getRedPenalties());
        assertEquals(0, actualFight.getRedVideoReplays());
        assertEquals("Tolian", actualFight.getBlueName());
        assertEquals(0, actualFight.getBlueScores());
        assertEquals(0, actualFight.getBluePenalties());
        assertEquals(0, actualFight.getBlueVideoReplays());
    }
}