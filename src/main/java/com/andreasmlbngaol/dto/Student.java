package com.andreasmlbngaol.dto;

import com.andreasmlbngaol.enums.Gender;
import com.andreasmlbngaol.enums.Major;
import com.andreasmlbngaol.enums.Religion;
import com.andreasmlbngaol.enums.Role;

public class Student extends User {
    private final String studentId;
    private final String major;
    private final double gpa;

    public Student(
            long userId,
            String email,
            String firstName,
            String lastname,
            Role role,
            Gender gender,
            Religion religion,
            String studentId,
            Major major,
            double gpa
    ) {
        super(userId, email, firstName, lastname, role, gender, religion);
        this.studentId = studentId;
        this.major = major.toString();
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.format(
                "Student(id=%d, email=\"%s\", firstName=\"%s\", lastName=\"%s\", role=\"%s\", gender=\"%s\", religion=\"%s\", studentId=\"%s\", major=\"%s\", gpa=%.2f)",
                super.id, super.email, super.firstName, super.lastName, super.role, super.gender, super.religion, studentId, major, gpa
        );
    }
}
