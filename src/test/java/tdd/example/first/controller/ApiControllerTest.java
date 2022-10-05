package tdd.example.first.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc
public class ApiControllerTest {
    String EXPECTED_METHOD_STRING = "GET";
    String EXPECTED_RESULT_STRING = "{\"id\":0,\"title\":\"notice0\",\"content\":\"content of notice0\",\"regDate\":\"2022-09-30T00:00:00\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRestControllerGetMapping() throws Exception {

        MvcResult result = (MvcResult) mockMvc.perform(get("/api/test_url"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("restController Test")))
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals(EXPECTED_METHOD_STRING, httpMethod);
    }


    @Test
    public void testRestControllerGetModel() throws Exception {

        MvcResult result = (MvcResult) mockMvc.perform(get("/api/notice"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String resultString = result.getResponse().getContentAsString();

        assertEquals(resultString.contains("id"), true);
        assertEquals(resultString.contains("title"), true);
        assertEquals(resultString.contains("content"), true);
        assertEquals(resultString.contains("regDate"), true);

    }

    @Test
    public void testRestControllerGetModelList() throws Exception {

        MvcResult result = (MvcResult) mockMvc.perform(get("/api/notice2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        String expectedString = "[{\"id\":0,\"title\":\"notice0\",\"content\":\"content of notice0\",\"regDate\":\"2022-09-30T00:00:00\"},{\"id\":1,\"title\":\"notice1\",\"content\":\"content of notice1\",\"regDate\":\"2022-09-30T00:01:00\"}]";
        assertEquals(resultString, expectedString);

    }

    @Test
    public void testRestControllerIsNotNull() throws Exception {

        ApiController controller = new ApiController();
        try{
            controller.isNotNull(null);
        }
        catch (Exception e){
            assertEquals(e.getMessage(),"exception, caused by null list");
        }

    }

}
