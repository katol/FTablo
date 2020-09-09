package FTablo.repositories;

import FTablo.entities.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltiesRepository extends JpaRepository<Penalty, Integer> {
}
