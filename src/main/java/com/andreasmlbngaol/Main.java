package com.andreasmlbngaol;

import com.andreasmlbngaol.db.Database;
import com.andreasmlbngaol.dto.Student;
import com.andreasmlbngaol.dto.Teacher;
import com.andreasmlbngaol.dto.User;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.entity.TeacherEntity;
import com.andreasmlbngaol.entity.UserEntity;
import com.andreasmlbngaol.enums.*;
import com.andreasmlbngaol.repository.Repository;
import com.andreasmlbngaol.service.AccountService;
import com.andreasmlbngaol.service.UserConverter;
import com.andreasmlbngaol.util.PasswordManager;

public class Main {
    private static final Repository repo = new Repository();

    public static void main(String[] args) {
        Database.init();



        Database.shutdown();
    }
}

//        var newStudent = new StudentEntity(
//                "lgandre45@gmail.com",
//                "password123",
//                "Andreas",
//                "Lumban Gaol",
//                Gender.MALE,
//                Religion.PROTESTANT,
//                "221401067",
//                Major.COMPUTER_SCIENCE,
//                0.0
//        );
//
//        var newTeacher = new TeacherEntity(
//                "jostarigan@usu.ac.id",
//                "hidupJava",
//                "Jos",
//                "Timanta Tarigan",
//                Gender.MALE,
//                Religion.PROTESTANT,
//                "198501262015041001",
//                Major.COMPUTER_SCIENCE,
//                AcademicRank.LECTURER
//        );
//
//        AccountService.registerStudent(newStudent, repo);
//        AccountService.registerTeacher(newTeacher, repo);
//
//        var myUser = repo.getUserById(1);
//        System.out.println(PasswordManager.verifyPassword("password123", myUser.getPasswordHash()));
//
//        var users = repo.getAllUsers();
//        for (UserEntity userEntity : users) {
//            User user = UserConverter.toDTO(userEntity);
//            if (user.getRole() == Role.STUDENT) {
//                var student = (Student) user;
//                System.out.println(student);
//            } else {
//                var teacher = (Teacher) user;
//                System.out.println(teacher);
//            }
//        }
