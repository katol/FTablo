package FTablo;

import FTablo.repositories.FightsRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value ="/fights", method = RequestMethod.GET)
    @ResponseBody
    public String getFights() {
        return new Gson().toJson(fightsRepository.findAll());
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    @ResponseBody
    public String takeExchange() {
        System.out.println("exchange");
        return "result";
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
