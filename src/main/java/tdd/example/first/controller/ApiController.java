package tdd.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.example.first.model.NoticeModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/api/notice2")
    public List<NoticeModel> getNotice2(){
        List<NoticeModel> noticeList = new ArrayList<>();

        NoticeModel notice1 = new NoticeModel();
        notice1.setId(0);
        notice1.setTitle("notice0");
        notice1.setContent("content of notice0");
        LocalDateTime regDate = LocalDateTime.of(2022,9,30,0,0);
        notice1.setRegDate(regDate);

        LocalDateTime regDate2 = LocalDateTime.of(2022,9,30,0,1);
        noticeList.add(notice1);
        // builder
        noticeList.add(NoticeModel.builder()
                .id(1)
                .title("notice1")
                .content("content of notice1")
                .regDate(regDate2)
                .build());
        if (isNoticeNotNull(noticeList) == true){
            return noticeList;
        }
        else{
            // it must change to handling exception
            return null;
        }

    }

    // Todo : if size<0 then handling exeption
    public boolean isNoticeNotNull(List<NoticeModel> noticeList){
        if (noticeList.size() > 0){
            return true;
        }
        else{
            return false;
        }

    }

}
