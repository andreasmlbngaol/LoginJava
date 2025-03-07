package com.andreasmlbngaol.service;

import com.andreasmlbngaol.dto.Student;
import com.andreasmlbngaol.dto.Teacher;
import com.andreasmlbngaol.dto.User;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.entity.TeacherEntity;
import com.andreasmlbngaol.entity.UserEntity;

public class UserConverter {
    public static User toDTO(UserEntity userEntity) {
        if(userEntity instanceof StudentEntity studentEntity) {
            return new Student(
                    studentEntity.getId(),
                    studentEntity.getEmail(),
                    studentEntity.getFirstName(),
                    studentEntity.getLastName(),
                    studentEntity.getRole(),
                    studentEntity.getGender(),
                    studentEntity.getReligion(),
                    studentEntity.getStudentId(),
                    studentEntity.getMajor(),
                    studentEntity.getGpa()
            );
        } else if(userEntity instanceof TeacherEntity teacherEntity) {
            return new Teacher(
                    teacherEntity.getId(),
                    teacherEntity.getEmail(),
                    teacherEntity.getFirstName(),
                    teacherEntity.getLastName(),
                    teacherEntity.getRole(),
                    teacherEntity.getGender(),
                    teacherEntity.getReligion(),
                    teacherEntity.getTeacherId(),
                    teacherEntity.getDepartment(),
                    teacherEntity.getAcademicRank()
            );
        } else {
            return new User(
                    userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getRole(),
                    userEntity.getGender(),
                    userEntity.getReligion()
            );
        }
    }
}
