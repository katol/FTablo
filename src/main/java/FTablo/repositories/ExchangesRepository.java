package FTablo.repositories;

import FTablo.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangesRepository extends JpaRepository<Exchange, Integer> {
}
