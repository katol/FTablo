package FTablo.entities;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FightTest {

    @Test
    void addExchange() {
        Fight fight = new Fight("Sveta", "Tolian");
        fight.setSecondsPassed(10);
        fight.setLastExchangeNumber(1);
        Timestamp oldTs = Timestamp.from(Instant.now());
        fight.setLastTs(oldTs);
        fight.setLastDescription("Some old description");
        fight.setRedScores(1);
        fight.setRedPenalties(0);
        fight.setRedVideoReplays(1);
        fight.setBlueScores(2);
        fight.setBluePenalties(1);
        fight.setBlueVideoReplays(0);

        Exchange exchange = new Exchange();
        exchange.setSecondsPassed(30);
        Timestamp newTs = Timestamp.from(Instant.now());
        exchange.setSaveTs(newTs);
        exchange.setActionDescription("Some exchange description");
        exchange.setScoresToRed(2);
        exchange.setScoresToBlue(1);

        fight.addExchange(exchange);

        assertEquals(30, fight.getSecondsPassed());
        assertEquals(2, fight.getLastExchangeNumber());
        assertEquals(newTs, fight.getLastTs());
        assertEquals("Some exchange description", fight.getLastDescription());
        assertEquals("Sveta", fight.getRedName());
        assertEquals(3, fight.getRedScores());
        assertEquals(0, fight.getRedPenalties());
        assertEquals(1, fight.getRedVideoReplays());
        assertEquals("Tolian", fight.getBlueName());
        assertEquals(3, fight.getBlueScores());
        assertEquals(1, fight.getBluePenalties());
        assertEquals(0, fight.getBlueVideoReplays());
    }
}