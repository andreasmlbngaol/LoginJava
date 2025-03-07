package com.andreasmlbngaol.entity;

import com.andreasmlbngaol.enums.Gender;
import com.andreasmlbngaol.enums.Major;
import com.andreasmlbngaol.enums.Religion;
import com.andreasmlbngaol.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class StudentEntity extends UserEntity {

    @Column(nullable = false, unique = true, name = "student_id")
    private String studentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Major major;

    @Column(columnDefinition = "double precision DEFAULT 0.0")
    private double gpa;

    public StudentEntity() {
    }

    // Constructor for Register
    public StudentEntity(
            String email,
            String originalPassword,
            String studentId,
            Major major
    ) {
        super(email, originalPassword, Role.STUDENT);
        this.studentId = studentId;
        this.major = major;
    }

    // Constructor for Test
    public StudentEntity(
            String email,
            String originalPassword,
            String firstName,
            String lastName,
            Gender gender,
            Religion religion,
            String studentId,
            Major major,
            double gpa
    ) {
        super(email, originalPassword, Role.STUDENT);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setGender(gender);
        super.setReligion(religion);
        this.studentId = studentId;
        this.major = major;
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

}
