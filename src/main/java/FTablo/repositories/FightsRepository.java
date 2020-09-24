package FTablo.repositories;

import FTablo.entities.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightsRepository extends JpaRepository<Fight, Integer> {
}
