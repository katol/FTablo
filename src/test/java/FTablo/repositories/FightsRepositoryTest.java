package FTablo.repositories;

import FTablo.entities.Fight;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void storesFights() {
        fightsRepository.save(new Fight("Sveta", "Tolian"));
    }
}