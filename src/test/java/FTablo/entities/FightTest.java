package FTablo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {

    Fight actual;

    @BeforeEach
    void setUp() {
        actual = new Fight();
        actual.setSecondsPassed(10);
        actual.setLastExchangeNumber(1);
        Timestamp oldTs = Timestamp.from(Instant.now());
        actual.setLastTs(oldTs);
        actual.setLastDescription("Some old description");
        actual.setRedName("Sveta");
        actual.setRedScores(1);
        actual.setRedPenalties(0);
        actual.setRedVideoReplays(1);
        actual.setBlueName("Tolian");
        actual.setBlueScores(2);
        actual.setBluePenalties(1);
        actual.setBlueVideoReplays(0);
    }

    @Test
    void addExchange() {
        Exchange exchange = new Exchange();
        exchange.setSecondsPassed(30);
        Timestamp newTs = Timestamp.from(Instant.now());
        exchange.setSaveTs(newTs);
        exchange.setActionDescription("Some exchange description");
        exchange.setScoresToRed(2);
        exchange.setScoresToBlue(1);

        actual.addExchange(exchange);

        Fight expected = new Fight();
        expected.setSecondsPassed(30);
        expected.setLastExchangeNumber(2);
        expected.setLastTs(newTs);
        expected.setLastDescription("Some exchange description");
        expected.setRedName("Sveta");
        expected.setRedScores(3);
        expected.setRedPenalties(0);
        expected.setRedVideoReplays(1);
        expected.setBlueName("Tolian");
        expected.setBlueScores(3);
        expected.setBluePenalties(1);
        expected.setBlueVideoReplays(0);

        assertEquals(expected, actual);
    }

    @Test
    void addPenalty() {
        Penalty penalty = new Penalty();
        penalty.setSecondsPassed(25);
        Timestamp newTs = Timestamp.from(Instant.now());
        penalty.setTs(newTs);
        penalty.setFoulDescription("Some foul description");
        penalty.setRuleBreakerColor(Color.BLUE);

        actual.addPenalty(penalty);

        Fight expected = new Fight();
        expected.setSecondsPassed(25);
        expected.setLastExchangeNumber(1);
        expected.setLastTs(newTs);
        expected.setLastDescription("Some foul description");
        expected.setRedName("Sveta");
        expected.setRedScores(1);
        expected.setRedPenalties(0);
        expected.setRedVideoReplays(1);
        expected.setBlueName("Tolian");
        expected.setBlueScores(2);
        expected.setBluePenalties(2);
        expected.setBlueVideoReplays(0);

        assertEquals(expected, actual);
    }
}