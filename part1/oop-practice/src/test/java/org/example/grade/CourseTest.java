package org.example.grade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CourseTest {

    @DisplayName("과목(코스)를 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new Course("OOP", 3, "A+"))
                .doesNotThrowAnyException();
    }

    @DisplayName("학점에 따른 점수 계산을 할 수 있다.")
    @ParameterizedTest
    @MethodSource("gradeAndNumber")
    void gradeToNumTest(String grade, double gradeNumber) {
        Course course = new Course("OOP", 3, grade);

        assertThat(course.getGradeToNumber()).isEqualTo(gradeNumber);
    }

    private static Stream<Arguments> gradeAndNumber() {
        return Stream.of(
                arguments("A+", 4.5),
                arguments("A", 4.0),
                arguments("B+", 3.5),
                arguments("B", 3.0),
                arguments("C+", 2.5),
                arguments("C", 2.0)
        );
    }

}
