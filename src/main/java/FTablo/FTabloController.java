package FTablo;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@Controller
public class FTabloController {
    private final Logger logger = LoggerFactory.getLogger(FTabloController.class);
    private final FightsRepository fightsRepository;
    private final TimerService timerService;

    public FTabloController(FightsRepository fightsRepository, TimerService timerService) {
        this.fightsRepository = fightsRepository;
        this.timerService = timerService;
    }

    @GetMapping("/secretary")
    public String secretary() {
        return "secretary";
    }

    @GetMapping("/viewer")
    public String viewer() {
        return "viewer";
    }

    @RequestMapping(value ="/fights", method = RequestMethod.POST)
    @ResponseBody
    public String createFight(@RequestBody Map<String, Object> payload) {
        Fight newFight = new Fight(
                (String) payload.get("redName"),
                (String) payload.get("blueName")
        );
        Fight savedFight = fightsRepository.save(newFight);
        return new Gson().toJson(savedFight);
    }

    @RequestMapping(value ="/fights", method = RequestMethod.GET)
    @ResponseBody
    public String getFights() {
        logger.info("Get fights");
        return new Gson().toJson(fightsRepository.findAll());
    }

    @RequestMapping(value = "/exchanges", method = RequestMethod.POST)
    @ResponseBody
    public String createExchange(@RequestBody Map<String, Object> payload) {
        Integer fightToUpdateId = (Integer) payload.get("fightId");
        Fight fightToUpdate = fightsRepository.findById(fightToUpdateId).orElse(null);
        if (fightToUpdate != null) {
            Exchange newExchange = new Exchange(
                    fightToUpdate,
                    (Integer) payload.get("secondsPassed"),
                    Timestamp.valueOf((String) payload.get("saveTs")),
                    "",
                    (Integer) payload.get("scoresToRed"),
                    (Integer) payload.get("scoresToBlue")
            );
            fightToUpdate.addExchange(newExchange);
            Fight updatedFight = fightsRepository.save(fightToUpdate);
            return new Gson().toJson(updatedFight);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/penalty", method = RequestMethod.POST)
    @ResponseBody
    public String takePenalty() {
        System.out.println("penalty");
        return "result";
    }

    @RequestMapping(value = "/videoReplay", method = RequestMethod.POST)
    @ResponseBody
    public String takeVideoReplay() {
        System.out.println("videoReplay");
        return "result";
    }

    @MessageMapping("/timer")
    public void timer(String message) {
        switch (message) {
            case "start":
                timerService.start();
                break;
            case "stop":
                timerService.stop();
                break;
            case "increase":
                timerService.increase();
                break;
            case "decrease":
                timerService.decrease();
                break;
            default:
                logger.warn("Unknown timer command " + message);
                break;
        }
    }
}
