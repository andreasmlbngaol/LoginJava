package com.andreasmlbngaol.dto;

import com.andreasmlbngaol.enums.*;

public class Teacher extends User {
    private final String teacherId;
    private final Major department;
    private final AcademicRank academicRank;

    public Teacher(
            long userId,
            String email,
            String firstName,
            String lastname,
            Role role,
            Gender gender,
            Religion religion,
            String teacherId,
            Major department,
            AcademicRank academicRank
    ) {
        super(userId, email, firstName, lastname, role, gender, religion);
        this.teacherId = teacherId;
        this.department = department;
        this.academicRank = academicRank;
    }

    public Major getDepartment() {
        return department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    @Override
    public String toString() {
        return String.format(
                "Teacher(id=%d, email=\"%s\", firstName=\"%s\", lastName=\"%s\", role=\"%s\", gender=\"%s\", religion=\"%s\", teacherId=\"%s\", department=\"%s\", academicRank=\"%s\")",
                super.id, super.email, super.firstName, super.lastName, super.role, super.gender, super.religion, teacherId, department, academicRank
        );
    }

}
