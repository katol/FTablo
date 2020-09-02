package FTablo.repositories;

import FTablo.entities.Fight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;
import java.time.Instant;
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
        expectedFight.setLastTs(Timestamp.from(Instant.now()));
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
}