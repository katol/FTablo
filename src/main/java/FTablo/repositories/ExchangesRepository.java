package FTablo.repositories;

import FTablo.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangesRepository extends JpaRepository<Exchange, Integer> {
}
