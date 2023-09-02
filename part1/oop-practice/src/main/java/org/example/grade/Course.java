package org.example.grade;

import java.util.List;

public class Course {

    private final String subject;
    private final int credit;
    private final String grade; // A+, A, B+, ...

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public double getGradeToNumber() {
        // 학점을 List를 사용하여 인덱스와 학점 점수를 매핑
        // A = 4
        // B = 3
        // C = 2
        // ...
        List<String> grades = List.of("F", "D", "C", "B", "A");
        double grade = grades.indexOf(this.grade.substring(0, 1));

        // 학점에 +가 있다면 0.5를 추가합니다.
        if (this.grade.contains("+")) {
            grade += 0.5;
        }
        return grade;
    }
}
