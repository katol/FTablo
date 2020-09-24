package FTablo;

import FTablo.entities.Exchange;
import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@Controller
public class FTabloController {
    private final FightsRepository fightsRepository;

    public FTabloController(FightsRepository fightsRepository) {
        this.fightsRepository = fightsRepository;
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
}
