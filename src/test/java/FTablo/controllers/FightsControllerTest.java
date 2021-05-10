package FTablo.controllers;

import FTablo.services.FightsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FightsController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
class FightsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FightsService fightsService;

    @Test
    void createFight_returnsStatus201() throws Exception {
        String json = "{ \"redName\":\"Sveta\", \"blueName\":\"Tolian\" }";
        mockMvc.perform(post("/fights").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated()).andDo(document("fights/create"));
    }

    @Test
    void createFight_throwsException_WhenInvalidParameters() throws Exception {
        String json = "{  }";
        mockMvc.perform(post("/fights").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isBadRequest())
                .andDo(document("fights/create-with-invalid-parameters"));
    }

    @Test
    void getFights_returnsStatus200() throws Exception {
        mockMvc.perform(get("/fights")).andDo(print()).andExpect(status().isOk())
                .andDo(document("fights/get-all"));
    }

    @Test
    void getFight_returnsStatus200() throws Exception {
        mockMvc.perform(get("/fights/1")).andDo(print()).andExpect(status().isOk())
                .andDo(document("fights/get-by-id"));
    }

    @Test
    void addExchangeToFight() throws Exception {
        String json = "{ \"secondsPassed\":25, \"saveTs\":\"2020-09-23T08:12:56.7747\" }";
        mockMvc.perform(post("/fights/1/exchanges").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated()).andDo(document("fights/add-exchange"));
    }

    @Test
    void addExchangeToFight_throwsException_WhenInvalidParameters() throws Exception {
        String json = "{  }";
        mockMvc.perform(post("/fights/1/exchanges").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isBadRequest())
                .andDo(document("fights/add-exchange-with-invalid-parameters"));
    }
}