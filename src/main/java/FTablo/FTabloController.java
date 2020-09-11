package FTablo;

import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FTabloController {
    @GetMapping("/secretary")
    public String secretary() {
        return "secretary";
    }

    @GetMapping("/fanse")
    public String fanse() {
        return "fanse";
    }

    @RequestMapping(value ="/secretary/fights", method = RequestMethod.GET)
    @ResponseBody
    public String getFights() {
        JSONObject firstFight = new JSONObject();
        JSONObject secondFight = new JSONObject();
        JSONObject resFight = new JSONObject();
        List<JSONObject> list = new ArrayList<>();
        firstFight.put("id_serial", "1");
        firstFight.put("seconds_passed", "12");
        firstFight.put("end_description", "12:00:00");
        firstFight.put("red_name", "Вася");
        firstFight.put("red_scores", "5");
        firstFight.put("blue_name", "Петя");
        firstFight.put("blue_scores", "7");
        secondFight.put("id_serial", "2");
        secondFight.put("seconds_passed", "13");
        secondFight.put("end_description", "13:00:00");
        secondFight.put("red_name", "Маша");
        secondFight.put("red_scores", "2");
        secondFight.put("blue_name", "Таня");
        secondFight.put("blue_scores", "8");
        list.add(firstFight);
        list.add(secondFight);
        resFight.put("res", list);
        return resFight.toString();
    }
    @RequestMapping(value ="/secretary/newFight", method = RequestMethod.GET)
    @ResponseBody
    public String getNewFight() {
        JSONObject firstFight = new JSONObject();
        firstFight.put("fight_id", 122);
        return firstFight.toString();
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
    @MessageMapping("/timer")
    @SendTo("/time")
    public JSONObject timerToJs() throws Exception {
        Thread.sleep(1000);
        JSONObject Json = new JSONObject();
        Json.put("time", 122);
        return Json;
    }

}
