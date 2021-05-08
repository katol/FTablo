package FTablo;

import FTablo.services.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FTabloController {
    private final Logger logger = LoggerFactory.getLogger(FTabloController.class);
    private final TimerService timerService;

    public FTabloController(TimerService timerService) {
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
