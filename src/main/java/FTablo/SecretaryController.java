package FTablo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecretaryController {
    @GetMapping("/secretary")
    public String secretary() {
        return "secretary";
    }

    public String fance() {
        return "fance";
    }
}
