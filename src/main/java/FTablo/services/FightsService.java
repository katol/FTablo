package FTablo.services;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import FTablo.exceptions.NoSuchFightException;
import FTablo.repositories.ExchangesRepository;
import FTablo.repositories.FightsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightsService {

    private final FightsRepository fightsRepository;
    private final ExchangesRepository exchangeRepository;

    public FightsService(FightsRepository fightsRepository, ExchangesRepository exchangeRepository) {
        this.fightsRepository = fightsRepository;
        this.exchangeRepository = exchangeRepository;
    }

    public Fight createFight(Fight fight) {
        return fightsRepository.save(fight);
    }

    public List<Fight> getFights() {
        return fightsRepository.findAll();
    }

    public Fight getFight(Integer id) {
        return fightsRepository.findById(id).orElseThrow(() -> new NoSuchFightException(id));
    }

    public Exchange addExchangeToFight(Integer fightId, Exchange exchange) {
        Fight fightToUpdate = getFight(fightId);
        fightToUpdate.addExchange(exchange);
        return exchangeRepository.save(exchange);
    }
}
