package tdd.example.first.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeModel {
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDate;

}
