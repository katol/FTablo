package FTablo.repositories;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ExchangesRepositoryTest {
    @Autowired
    private Flyway flyway;

    @Autowired
    private FightsRepository fightsRepository;

    @Autowired
    private ExchangesRepository exchangesRepository;

    private Fight fight;
    private Exchange exchange;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();

        fight = new Fight("Sveta", "Tolian");
        fight.setSecondsPassed(10);
        fight.setLastExchangeNumber(1);
        fight.setLastTs(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS)));
        fight.setLastDescription("Some description");
        fight.setRedScores(1);
        fight.setRedPenalties(0);
        fight.setRedVideoReplays(1);
        fight.setBlueScores(2);
        fight.setBluePenalties(1);
        fight.setBlueVideoReplays(0);

        exchange = new Exchange();
        exchange.setSecondsPassed(10);
        exchange.setSaveTs(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS)));
        exchange.setActionDescription("Some action description");
        exchange.setScoresToRed(1);
        exchange.setScoresToBlue(2);
    }

    @Test
    void storesExchangeWithFight() {
        fight.addExchange(exchange);
        fightsRepository.save(fight);
        assertEquals(1, exchangesRepository.count());
    }

    @Test
    void doesNotStoreExchangeWithoutFight() {
        assertThrows(DataIntegrityViolationException.class, () -> exchangesRepository.save(exchange));
    }
}