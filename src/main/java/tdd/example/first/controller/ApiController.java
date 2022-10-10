package tdd.example.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdd.example.first.entity.Notice;
import tdd.example.first.model.NoticeInput;
import tdd.example.first.model.NoticeModel;
import tdd.example.first.repository.NoticeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class ApiController {

    private final NoticeRepository noticeRepository;

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

        // setter
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

        try {
            if((isNotNull(noticeList) == true)){
                return noticeList;
            }
        } catch (Exception e) {
            // Pass
        }
        return null;
    }

    public boolean isNotNull(List<NoticeModel> noticeList) throws Exception {
        if(noticeList != null){
            if (noticeList.size() > 0){
                return true;
            }
        }
        else{
            throw new Exception("exception, caused by null list");
        }
        return false;
    }

    @PostMapping("/api/requestDataToModelData")
    public NoticeModel responseModelData(@RequestParam String title, @RequestParam String content){
        NoticeModel model = NoticeModel.builder()
                .id(0)
                .content(content)
                .title(title)
                .regDate(null)
                .build();

        return model;
    }

    @PostMapping("/api/requestDataToModelData2")
    public NoticeModel responseModelData2(@ModelAttribute NoticeModel model){
        model.setRegDate(null);
        model.setId(0);
        return model;
    }

    @PostMapping("/api/requestDataToModelDataJson")
    public NoticeModel responseModelDataJson(@RequestBody NoticeModel model){
        model.setRegDate(null);
        model.setId(0);
        return model;
    }


    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput){
        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .regDate(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);

        return notice;
    }

    @PostMapping("/api/notice2")
    public Notice addNotice2(@RequestBody NoticeInput noticeInput){
        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .regDate(LocalDateTime.now())
                .likes(0)
                .viewCnt(0)
                .build();
        Notice resultNotice = noticeRepository.save(notice);

        return resultNotice;
    }
    @GetMapping("/api/notice/{id}")
    public Notice notice(@PathVariable Long id){

        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isPresent()){
            return notice.get();
        }

        // 나중에 throw except 하는게 더 좋음
        return null;

    }
}
