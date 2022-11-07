package tdd.example.first.notice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;


    @Column
    private String content;

    @Column
    private LocalDateTime regDate;

    @Column
    private LocalDateTime updateDate;

    @Column
    private int viewCnt;

    @Column
    private int likes;


}
