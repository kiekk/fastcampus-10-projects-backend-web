package com.fastcampus.pass.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Repository 테스트이기 때문에 @DataJpaTest 를 사용해도 되지만
// 직접 수행되는 쿼리와 결과를 확인하기 위해 롤백 없이 테스트 작성
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {

    private final PackageRepository packageRepository;

    PackageRepositoryTest(@Autowired PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Test
    void testSave() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 챌린지 PT 12주");
        packageEntity.setPeriod(84);

        // when
        packageRepository.save(packageEntity);

        // then
        assertThat(packageEntity.getPackageSeq()).isNotNull();
    }

    @Test
    void test_findByCreatedAtAfter() {
        // given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntity0 = new PackageEntity();
        packageEntity0.setPackageName("학생 전용 3개월");
        packageEntity0.setPeriod(90);
        packageRepository.save(packageEntity0);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("학생 전용 6개월");
        packageEntity1.setPeriod(180);
        packageRepository.save(packageEntity1);

        // when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        // then
        assertThat(packageEntities).hasSize(1);
        assertThat(packageEntities.get(0)).isEqualTo(packageEntity1);
    }

    @Test
    void test_updateCountAndPeriod() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디프로필 이벤트 4개월");
        packageEntity.setPeriod(90);
        packageRepository.save(packageEntity);

        // when
        int updatedCount = packageRepository.updateCountAndPeriod(packageEntity.getPackageSeq(), 30, 120);
        final PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getPackageSeq()).get();

        // then
        assertThat(updatedCount).isEqualTo(1);
        assertThat(updatedPackageEntity.getCount()).isEqualTo(30);
        assertThat(updatedPackageEntity.getPeriod()).isEqualTo(120);
    }

    @Test
    void test_delete() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("제거할 이용권");
        packageEntity.setCount(1);
        PackageEntity createdPackageEntity = packageRepository.save(packageEntity);

        // when
        packageRepository.deleteById(createdPackageEntity.getPackageSeq());

        // then
        assertThat(packageRepository.findById(createdPackageEntity.getPackageSeq())).isEmpty();
    }

}
