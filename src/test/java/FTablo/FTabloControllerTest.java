package FTablo;

import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FTabloController.class)
class FTabloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FightsRepository fightsRepository;

    @Test
    void getFights() throws Exception {
        String json =
                "[\n" +
                        "\n" +
                        "    {\n" +
                        "        \"id\":1,\n" +
                        "        \"secondsPassed\":10,\n" +
                        "        \"lastExchangeNumber\":1,\n" +
                        "        \"lastTs\":\"Sep 13, 2020, 8:26:09 PM\",\n" +
                        "        \"lastDescription\":\"Some description\",\n" +
                        "        \"redName\":\"Sveta\",\n" +
                        "        \"redScores\":1,\n" +
                        "        \"redPenalties\":0,\n" +
                        "        \"redVideoReplays\":1,\n" +
                        "        \"blueName\":\"Tolian\",\n" +
                        "        \"blueScores\":2,\n" +
                        "        \"bluePenalties\":1,\n" +
                        "        \"blueVideoReplays\":0\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\":2,\n" +
                        "        \"secondsPassed\":11,\n" +
                        "        \"lastExchangeNumber\":1,\n" +
                        "        \"lastTs\":\"Sep 13, 2020, 8:27:07 PM\",\n" +
                        "        \"lastDescription\":\"Some description\",\n" +
                        "        \"redName\":\"Sveta\",\n" +
                        "        \"redScores\":1,\n" +
                        "        \"redPenalties\":0,\n" +
                        "        \"redVideoReplays\":1,\n" +
                        "        \"blueName\":\"Tolian\",\n" +
                        "        \"blueScores\":2,\n" +
                        "        \"bluePenalties\":1,\n" +
                        "        \"blueVideoReplays\":0\n" +
                        "    }\n" +
                        "\n" +
                "]";

        List<Fight> fights = new Gson().fromJson(json, new TypeToken<List<Fight>>() {}.getType());
        fights.forEach(fight -> fightsRepository.save(fight));

        mockMvc.perform(get("/fights").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isOk());
    }
}