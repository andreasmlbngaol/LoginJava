package com.andreasmlbngaol.entity;

import com.andreasmlbngaol.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class TeacherEntity extends UserEntity {

    @Column(nullable = false, unique = true, name = "teacher_id")
    private String teacherId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Major department;

    @Column(nullable = false, name = "academic_rank")
    @Enumerated(EnumType.STRING)
    private AcademicRank academicRank;

    public TeacherEntity() {
    }

    // Constructor for Test
    public TeacherEntity(
            String email,
            String originalPassword,
            String firstName,
            String lastName,
            Gender gender,
            Religion religion,
            String teacherId,
            Major department,
            AcademicRank academicRank
    ) {
        super(email, originalPassword, Role.TEACHER);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setGender(gender);
        super.setReligion(religion);
        this.teacherId = teacherId;
        this.department = department;
        this.academicRank = academicRank;
    }

    public Major getDepartment() {
        return department;
    }

    public void setDepartment(Major department) {
        this.department = department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }
}
