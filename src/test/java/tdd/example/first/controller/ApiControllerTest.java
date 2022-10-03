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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc
public class ApiControllerTest {
    String EXPECTED_METHOD_STRING = "GET";

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
        assertEquals(EXPECTED_METHOD_STRING,httpMethod);
    }
}
