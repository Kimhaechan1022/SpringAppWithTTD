package tdd.example.first.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdd.example.first.notice.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
