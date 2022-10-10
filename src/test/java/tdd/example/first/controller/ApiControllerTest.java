package tdd.example.first.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tdd.example.first.repository.NoticeRepository;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(ApiController.class)
//@MockBean(NoticeRepository.class)
//@WebMvcTest(ApiController.class) //unit class context
//@SpringBootTest   //full application context
//@MockBean // repository bean adding
@SpringBootTest
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

//    @Test
//    public void testRestControllerIsNotNull() throws Exception {
//
//        ApiController controller = new ApiController();
//        try{
//            controller.isNotNull(null);
//        }
//        catch (Exception e){
//            assertEquals(e.getMessage(),"exception, caused by null list");
//        }
//
//    }

    @Test
    public void testRequestDataToModel() throws Exception {

        String requestTitle = "title1";
        String requestContent = "content1";

        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelData")
                        .param("title",requestTitle)
                        .param("content",requestContent))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST",httpMethod);


        String resultString = result.getResponse().getContentAsString();
        assertEquals(true,resultString.contains(requestTitle));
        assertEquals(true,resultString.contains(requestContent));

    }



    @Test
    public void testRequestDataToModel2() throws Exception {
        String contentString = "title=t1&content=c1";
        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelData2")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(contentString))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST",httpMethod);


        String resultString = result.getResponse().getContentAsString();
        assertEquals(true,resultString.contains("t1"));
        assertEquals(true,resultString.contains("c1"));

    }

    @Test
    public void testRequestDataToModel3() throws Exception {

        String content = "{\"title\": \"t1\", \"content\": \"c1\"}";

        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelDataJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST",httpMethod);

        String resultString = result.getResponse().getContentAsString();
        assertEquals(true,resultString.contains("t1"));
        assertEquals(true,resultString.contains("c1"));

    }

    @Test
    @DisplayName("Notice 정보에 좋아요와 조회수를 0으로 초기화하는 Test")
    public void testRequestDataToModel4() throws Exception {

        String content = "{\"title\": \"t\", \"content\": \"c\"}";

        MvcResult result = (MvcResult) mockMvc.perform(post("/api/notice2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST",httpMethod);

        String resultString = result.getResponse().getContentAsString();


    }

    @Test
    @DisplayName("id 로 조회된 데이터를 가저오는지 Test")
    public void testRequestDataToModel5() throws Exception {

        String content = "{\"title\": \"t\", \"content\": \"c\"}";

        MvcResult result = (MvcResult) mockMvc.perform(get("/api/notice/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();


    }

}
