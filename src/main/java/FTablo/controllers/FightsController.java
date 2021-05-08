package FTablo.controllers;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import FTablo.services.FightsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("fights")
public class FightsController {

    private final FightsService fightsService;

    public FightsController(FightsService fightsService) {
        this.fightsService = fightsService;
    }

    @PostMapping
    public ResponseEntity<Fight> createFight(@RequestBody @Valid Fight newFight) {
        Fight createdFight = fightsService.createFight(newFight);
        return new ResponseEntity<>(createdFight, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Fight>> getFights() {
        List<Fight> fights = fightsService.getFights();
        return new ResponseEntity<>(fights, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fight> getFight(@PathVariable Integer id) {
        Fight fight = fightsService.getFight(id);
        return new ResponseEntity<>(fight, HttpStatus.OK);
    }

    @PostMapping("{fightId}/exchanges")
    public ResponseEntity<Exchange> addExchangeToFight(@PathVariable Integer fightId,
                                                       @RequestBody @Valid Exchange newExchange) {
        Exchange createdExchange = fightsService.addExchangeToFight(fightId, newExchange);
        return new ResponseEntity<>(createdExchange, HttpStatus.CREATED);
    }
}
