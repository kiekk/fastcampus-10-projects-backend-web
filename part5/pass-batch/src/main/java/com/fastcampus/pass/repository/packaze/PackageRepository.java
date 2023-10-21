package com.fastcampus.pass.repository.packaze;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    List<PackageEntity> findByCreatedAtAfter(LocalDateTime dateTime, PageRequest packageSeq);

    // @Modifying 은 insert, update, delete 에서 사용됩니다.
    // update, delete 경우에는 트랜잭션이 없을 경우 에러가 발생하기 때문에
    // 현재 테스트를 위해 이 부분에 @Transactional 을 추가합니다.
    @Transactional
    @Modifying
    @Query(value = """
            UPDATE PackageEntity p 
            SET p.count = :count
            , p.period = :period
            WHERE p.packageSeq = :packageSeq
            """
    )
    int updateCountAndPeriod(Long packageSeq, int count, int period);
}
