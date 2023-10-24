package com.fastcampus.pass.service.statistics;

import com.fastcampus.pass.repository.statistics.StatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    @Mock // Mock 객체로 사용합니다.
    private StatisticsRepository statisticsRepository;
    @InjectMocks // @InjectMocks 클래스의 인스턴스를 생성하고 @Mock으로 생성된 객체를 주입합니다.
    private StatisticsService statisticsService;

    @Nested
    @DisplayName("통계 데이터를 기반으로 차트 만들기")
    class MakeChartData {
        final LocalDateTime to = LocalDateTime.of(2022, 9, 10, 0, 0);

        @DisplayName("통계 데이터가 있을 때")
        @Test
        void makeChartData_when_hasStatistics() {
            // given
            List<AggregatedStatistics> statisticsList = List.of(
                    new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
                    new AggregatedStatistics(to, 10, 8, 2)
            );

            // when
            when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(statisticsList);

            final ChartData chartData = statisticsService.makeChartData(to);

            // then
            verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

            assertThat(chartData).isNotNull();
            assertThat(chartData.getLabels()).isEqualTo(new ArrayList<>(List.of("09-09", "09-10")));
            assertThat(chartData.getAttendedCounts()).isEqualTo(new ArrayList<>(List.of(10L, 8L)));
            assertThat(chartData.getCancelledCounts()).isEqualTo(new ArrayList<>(List.of(5L, 2L)));
        }

        @DisplayName("통계 데이터가 없을 때")
        @Test
        void makeChartData_when_notHasStatistics() {
            // when
            when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(Collections.emptyList());

            final ChartData chartData = statisticsService.makeChartData(to);

            // then
            verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

            assertThat(chartData).isNotNull();
            assertThat(chartData.getLabels()).isEmpty();
            assertThat(chartData.getAttendedCounts()).isEmpty();
            assertThat(chartData.getCancelledCounts()).isEmpty();
        }

    }

    @DisplayName("차트 데이터 만들기")
    @Test
    public void test_makeChartData() {
        // given  to = 2022-09-10 00:00
        final LocalDateTime to = LocalDateTime.of(2022, 9, 10, 0, 0);

        List<AggregatedStatistics> statisticsList = List.of(
                new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
                new AggregatedStatistics(to, 10, 8, 2)
        );

        // when
        // statisticsRepository Mock 객체가 findByStatisticsAtBetweenAndGroupBy()를 실행할 때 statisticsList를 반환합니다.
        when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(statisticsList);

        final ChartData chartData = statisticsService.makeChartData(to);

        // then
        // findByStatisticsAtBetweenAndGroupBy()가 1번 호출되었는지 검증합니다.
        verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

        assertThat(chartData).isNotNull();
        assertThat(chartData.getLabels()).isEqualTo(new ArrayList<>(List.of("09-09", "09-10")));
        assertThat(chartData.getAttendedCounts()).isEqualTo(new ArrayList<>(List.of(10L, 8L)));
        assertThat(chartData.getCancelledCounts()).isEqualTo(new ArrayList<>(List.of(5L, 2L)));
    }

}
