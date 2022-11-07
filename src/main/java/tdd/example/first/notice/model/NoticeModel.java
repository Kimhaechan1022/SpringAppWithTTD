package tdd.example.first.notice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NoticeModel {
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDate;

}
