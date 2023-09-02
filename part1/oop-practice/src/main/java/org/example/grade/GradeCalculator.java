package org.example.grade;

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }

    /**
     * 요구사항
     * 평균학점 계산 방법 - (학점수*교과목 평점)의 합계 / 수강신청 총학점 수
     * 일급 컬렉션 사용
     */
    public double calculateGrade() {
        // (학점수*교과목 평점)의 합계
        double multipliedCreditAndCourseGrade = courses.getMultipliedCreditAndCourseGrade();

        // 수강신청 총학점 수
        int totalCompletedCredit = courses.getTotalCompletedCredit();
        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }

}
