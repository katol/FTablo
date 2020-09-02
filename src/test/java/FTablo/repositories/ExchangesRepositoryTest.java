package FTablo.repositories;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangesRepositoryTest {
    @Autowired
    private Flyway flyway;

    @Autowired
    private FightsRepository fightsRepository;

    @Autowired
    private ExchangesRepository exchangesRepository;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void savesExchange() {
        Exchange expectedExchange = new Exchange();

        Fight fight = new Fight();
        fight.setSecondsPassed(10);
        fight.setLastExchangeNumber(1);
        fight.setLastTs(Timestamp.from(Instant.now()));
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

        expectedExchange.setFight(fight);
        expectedExchange.setSecondsPassed(10);
        expectedExchange.setSaveTs(Timestamp.from(Instant.now()));
        expectedExchange.setActionDescription("Some action description");
        expectedExchange.setScoresToRed(1);
        expectedExchange.setScoresToBlue(2);

        exchangesRepository.save(expectedExchange);

        Exchange actualExchange = exchangesRepository.findAll().stream().findFirst().orElse(null);
        assertEquals(expectedExchange, actualExchange);
    }
}