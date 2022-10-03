package tdd.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.example.first.model.NoticeModel;
import java.time.LocalDateTime;

@RestController
public class ApiController {

    @GetMapping("/api/test_url")
    public String MappingRequest3(){
        return  "restController Test";
    }


    @GetMapping("/api/notice")
    public NoticeModel getNotice(){
        LocalDateTime regDate = LocalDateTime.of(2022,9,30,0,0);
        NoticeModel notice = new NoticeModel();
        notice.setId(0);
        notice.setTitle("notice0");
        notice.setContent("content of notice0");
        notice.setRegDate(regDate);
        return notice;
    }
}
