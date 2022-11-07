package tdd.example.first.notice.repository.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.example.first.notice.entity.Notice;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import tdd.example.first.notice.repository.NoticeRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    @DisplayName("repository save 메소드 결과와 entity가 매핑이 잘되는지 테스트")
    void saveNoticeTest() {
        String content = "content";
        String title = "title";
        Notice entity = Notice.builder()
                .content(content)
                .title(title)
                .regDate(LocalDateTime.now())
                .build();
        Notice savedEntity = noticeRepository.save(entity);
        assertEquals(entity.getContent(),savedEntity.getContent());
        assertEquals(entity.getTitle(),savedEntity.getTitle());
        assertEquals(entity.getRegDate(),savedEntity.getRegDate());
        assertEquals(entity.getId(),savedEntity.getId());
    }
    @Test
    @DisplayName("repository save 메소드 결과가 조회시 확인되는지 확인")
    void saveNoticeTest2() {
        String content = "content1";
        String title = "title";
        Notice entity = Notice.builder()
                .content(content)
                .title(title)
                .regDate(LocalDateTime.now())
                .build();
        Notice savedEntity = noticeRepository.save(entity);

        Optional<Notice> findEntity = noticeRepository.findById(savedEntity.getId());

        assertEquals(findEntity.get().getContent(),savedEntity.getContent());
        assertEquals(findEntity.get().getTitle(),savedEntity.getTitle());

    }
}
