package FTablo;

import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;

@SpringBootApplication
public class FTabloApplication {
	private static final Logger logger = LoggerFactory.getLogger(FTabloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FTabloApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FightsRepository fightsRepository) {
		return (args) -> {
			Fight fight = new Fight();
			fight.setSecondsPassed(10);
			fight.setEndTs(Timestamp.from(Instant.now()));
			fight.setEndDescription("Some description");
			fight.setRedName("Sveta");
			fight.setRedScores(1);
			fight.setBlueName("Tolian");
			fight.setBlueScores(2);

			fightsRepository.save(fight);

			logger.info("All fights list:");
			fightsRepository.findAll().forEach(f -> logger.info(f.toString()));
			logger.info("");
		};
	}
}
