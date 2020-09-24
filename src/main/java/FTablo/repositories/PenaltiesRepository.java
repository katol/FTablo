package FTablo.repositories;

import FTablo.entities.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltiesRepository extends JpaRepository<Penalty, Integer> {
}
