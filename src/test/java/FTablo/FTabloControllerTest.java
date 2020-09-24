package FTablo;

import FTablo.entities.Fight;
import FTablo.repositories.FightsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FTabloController.class)
class FTabloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FightsRepository fightsRepository;

    private Fight fight1;
    private Fight fight2;

    @BeforeEach
    void setUp() {
        fight1 = new Fight();
        fight1.setId(1);
        fight1.setSecondsPassed(10);
        fight1.setLastExchangeNumber(1);
        fight1.setLastTs(Timestamp.valueOf("2020-09-23 08:12:51.7747"));
        fight1.setLastDescription("Some description");
        fight1.setRedName("Sveta");
        fight1.setRedScores(1);
        fight1.setRedPenalties(1);
        fight1.setRedVideoReplays(2);
        fight1.setBlueName("Tolian");
        fight1.setBlueScores(2);
        fight1.setBluePenalties(3);
        fight1.setBlueVideoReplays(4);

        fight2 = new Fight();
        fight2.setId(2);
        fight2.setSecondsPassed(15);
        fight2.setLastExchangeNumber(2);
        fight2.setLastTs(Timestamp.valueOf("2020-09-23 08:12:56.7747"));
        fight2.setLastDescription("");
        fight2.setRedName("Sveta");
        fight2.setRedScores(2);
        fight2.setRedPenalties(1);
        fight2.setRedVideoReplays(2);
        fight2.setBlueName("Tolian");
        fight2.setBlueScores(4);
        fight2.setBluePenalties(3);
        fight2.setBlueVideoReplays(4);
    }

    @Test
    void createFight() throws Exception {
        Fight newFight = new Fight("Sveta", "Tolian");
        Fight savedFight = new Fight("Sveta", "Tolian");
        savedFight.setId(1);

        when(fightsRepository.save(newFight)).thenReturn(savedFight);

        String json = "{ \"redName\":\"Sveta\", \"blueName\":\"Tolian\" }";

        mockMvc.perform(post("/fights").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.redName", is("Sveta")))
                .andExpect(jsonPath("$.blueName", is("Tolian")));
    }

    @Test
    void getFights() throws Exception {
        when(fightsRepository.findAll()).thenReturn(Arrays.asList(fight1, fight2));

        mockMvc.perform(get("/fights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].secondsPassed", is(10)))
                .andExpect(jsonPath("$[0].lastExchangeNumber", is(1)))
                .andExpect(jsonPath("$[0].lastTs", is("Sep 23, 2020, 8:12:51 AM")))
                .andExpect(jsonPath("$[0].lastDescription", is("Some description")))
                .andExpect(jsonPath("$[0].redName", is("Sveta")))
                .andExpect(jsonPath("$[0].redScores", is(1)))
                .andExpect(jsonPath("$[0].redPenalties", is(1)))
                .andExpect(jsonPath("$[0].redVideoReplays", is(2)))
                .andExpect(jsonPath("$[0].blueName", is("Tolian")))
                .andExpect(jsonPath("$[0].blueScores", is(2)))
                .andExpect(jsonPath("$[0].bluePenalties", is(3)))
                .andExpect(jsonPath("$[0].blueVideoReplays", is(4)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].secondsPassed", is(15)))
                .andExpect(jsonPath("$[1].lastExchangeNumber", is(2)))
                .andExpect(jsonPath("$[1].lastTs", is("Sep 23, 2020, 8:12:56 AM")))
                .andExpect(jsonPath("$[1].lastDescription", is("")))
                .andExpect(jsonPath("$[1].redName", is("Sveta")))
                .andExpect(jsonPath("$[1].redScores", is(2)))
                .andExpect(jsonPath("$[1].redPenalties", is(1)))
                .andExpect(jsonPath("$[1].redVideoReplays", is(2)))
                .andExpect(jsonPath("$[1].blueName", is("Tolian")))
                .andExpect(jsonPath("$[1].blueScores", is(4)))
                .andExpect(jsonPath("$[1].bluePenalties", is(3)))
                .andExpect(jsonPath("$[1].blueVideoReplays", is(4)));
    }

    @Test
    void createExchange() throws Exception {
        when(fightsRepository.findById(1)).thenReturn(Optional.of(fight1));
        fight2.setId(1);
        when(fightsRepository.save(fight2)).thenReturn(fight2);

        String json =
                "{" +
                        "\"fightId\":1," +
                        "\"secondsPassed\":15," +
                        "\"saveTs\":\"2020-09-23 08:12:56.7747\"," +
                        "\"actionDescription\":\"\"," +
                        "\"scoresToRed\":1," +
                        "\"scoresToBlue\":2" +
                        "}";

        mockMvc.perform(post("/exchanges").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.secondsPassed", is(15)))
                .andExpect(jsonPath("$.lastExchangeNumber", is(2)))
                .andExpect(jsonPath("$.lastTs", is("Sep 23, 2020, 8:12:56 AM")))
                .andExpect(jsonPath("$.lastDescription", is("")))
                .andExpect(jsonPath("$.redName", is("Sveta")))
                .andExpect(jsonPath("$.redScores", is(2)))
                .andExpect(jsonPath("$.redPenalties", is(1)))
                .andExpect(jsonPath("$.redVideoReplays", is(2)))
                .andExpect(jsonPath("$.blueName", is("Tolian")))
                .andExpect(jsonPath("$.blueScores", is(4)))
                .andExpect(jsonPath("$.bluePenalties", is(3)))
                .andExpect(jsonPath("$.blueVideoReplays", is(4)));
    }
}