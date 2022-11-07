package tdd.example.first.notice.repository.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tdd.example.first.notice.controller.AddressRequestController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AddressRequestController.class)
@AutoConfigureMockMvc
public class AddressRequestControllerTest {

    String EXPECTED_METHOD_STRING = "GET";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddressRequest() throws Exception {
        MvcResult result = (MvcResult) mockMvc.perform(get("/test_url"))
                .andExpect(status().isOk())
                .andExpect(view().name("addressRequest"))
                .andExpect(content().string(
                        containsString("This is address request tdd example")))
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();

        assertEquals(EXPECTED_METHOD_STRING,httpMethod);

    }

    @Test
    public void testGetMapping() throws Exception {
        MvcResult result = (MvcResult) mockMvc.perform(get("/test_url2"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("This is response of get mapping")))
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();

        assertEquals(EXPECTED_METHOD_STRING,httpMethod);
    }
}



