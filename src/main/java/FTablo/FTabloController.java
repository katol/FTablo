package FTablo;

import org.json.JSONObject;
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
    public String GetFights() {
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

}
