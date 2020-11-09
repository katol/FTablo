package FTablo.repositories;

import FTablo.Color;
import FTablo.entities.Fight;
import FTablo.entities.Penalty;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PenaltiesRepositoryTest {
    @Autowired
    private Flyway flyway;

    @Autowired
    private FightsRepository fightsRepository;

    @Autowired
    private PenaltiesRepository penaltiesRepository;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void savesPenalty() {
        Penalty expectedPenalty = new Penalty();

        Fight fight = new Fight();
        fight.setSecondsPassed(10);
        fight.setLastExchangeNumber(1);
        fight.setLastTs(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS)));
        fight.setLastDescription("Some description");
        fight.setRedName("Sveta");
        fight.setRedScores(1);
        fight.setRedPenalties(0);
        fight.setRedVideoReplays(1);
        fight.setBlueName("Tolian");
        fight.setBlueScores(2);
        fight.setBluePenalties(1);
        fight.setBlueVideoReplays(0);

        fightsRepository.save(fight);

        expectedPenalty.setFight(fight);
        expectedPenalty.setSecondsPassed(10);
        expectedPenalty.setTs(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS)));
        expectedPenalty.setFoulDescription("Some action description");
        expectedPenalty.setRuleBreakerColor(Color.BLUE);

        penaltiesRepository.save(expectedPenalty);

        Penalty actualPenalty = penaltiesRepository.findAll().stream().findFirst().orElse(null);
        assertEquals(expectedPenalty, actualPenalty);
    }
}