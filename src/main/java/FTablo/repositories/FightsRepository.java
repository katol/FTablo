package FTablo.repositories;

import FTablo.entities.Fight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FightsRepository extends JpaRepository<Fight, Integer> {
}
