package FTablo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
