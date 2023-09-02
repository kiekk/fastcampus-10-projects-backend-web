package org.example.grade;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double getMultipliedCreditAndCourseGrade() {
        return courses.stream().mapToDouble(Course::getMultiplyCreditAndCoursedGrade).sum();
    }

    public int getTotalCompletedCredit() {
        return courses.stream().mapToInt(Course::getCredit).sum();
    }

}
