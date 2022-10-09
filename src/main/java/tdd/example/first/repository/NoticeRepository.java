package tdd.example.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdd.example.first.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
