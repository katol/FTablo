package FTablo;

import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class FTabloApplication {
	private static FightsRepository fightsRepository;

	public FTabloApplication(FightsRepository fightsRepository) {
		FTabloApplication.fightsRepository = fightsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FTabloApplication.class, args);
//		addFight();
	}

	private static void addFight() {
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
	}
}