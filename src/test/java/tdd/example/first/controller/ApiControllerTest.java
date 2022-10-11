package tdd.example.first.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(NoticeRepository.class)
public class ApiControllerTest {
    String EXPECTED_METHOD_STRING = "GET";
    String EXPECTED_RESULT_STRING = "{\"id\":0,\"title\":\"notice0\",\"content\":\"content of notice0\",\"regDate\":\"2022-09-30T00:00:00\"}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    @DisplayName("Get 요청 동작")
    public void testHttpGetMethod() throws Exception {

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
    @DisplayName("Get 요청 응답")
    public void testGetMethodResponse() throws Exception {

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
    @DisplayName("다수의 모델을 포함한 리스트 검증")
    public void testModelList() throws Exception {

        MvcResult result = (MvcResult) mockMvc.perform(get("/api/noticeList"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        String expectedString = "[{\"id\":0,\"title\":\"notice0\",\"content\":\"content of notice0\",\"regDate\":\"2022-09-30T00:00:00\"},{\"id\":1,\"title\":\"notice1\",\"content\":\"content of notice1\",\"regDate\":\"2022-09-30T00:01:00\"}]";
        assertEquals(resultString, expectedString);

    }

    @Test
    public void testRestControllerIsNotNull() throws Exception {

        ApiController controller = new ApiController(noticeRepository);
        try{
            controller.isNotNull(null);
        }
        catch (Exception e){
            assertEquals(e.getMessage(),"exception, caused by null list");
        }

    }

    @Test
    @DisplayName(" request parameter 지정")
    public void testRequestDataToModel() throws Exception {

        String requestTitle = "title1";
        String requestContent = "content1";

        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelData")
                        .param("title", requestTitle)
                        .param("content", requestContent))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST", httpMethod);


        String resultString = result.getResponse().getContentAsString();
        assertEquals(true, resultString.contains(requestTitle));
        assertEquals(true, resultString.contains(requestContent));

    }


    @Test
    @DisplayName("form_urlencoded 방식의 요청")
    public void testRequestDataToModel2() throws Exception {
        String contentString = "title=t1&content=c1";
        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelData2")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(contentString))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST", httpMethod);


        String resultString = result.getResponse().getContentAsString();
        assertEquals(true, resultString.contains("t1"));
        assertEquals(true, resultString.contains("c1"));

    }

    @Test
    @DisplayName("json 방식의 요청")
    public void testRequestDataToModel3() throws Exception {

        String content = "{\"title\": \"t1\", \"content\": \"c1\"}";

        MvcResult result = (MvcResult) mockMvc.perform(post("/api/requestDataToModelDataJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String httpMethod = result.getRequest().getMethod();
        assertEquals("POST", httpMethod);

        String resultString = result.getResponse().getContentAsString();
        assertEquals(true, resultString.contains("t1"));
        assertEquals(true, resultString.contains("c1"));

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
        assertEquals("POST", httpMethod);

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
