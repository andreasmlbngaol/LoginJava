package com.andreasmlbngaol;

import com.andreasmlbngaol.db.Database;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.enums.Gender;
import com.andreasmlbngaol.enums.Major;
import com.andreasmlbngaol.enums.Religion;
import com.andreasmlbngaol.repository.DatabaseRepository;
import com.andreasmlbngaol.service.AccountService;

public class Main {
    private static final DatabaseRepository repo = new DatabaseRepository();

    public static void main(String[] args) {
        Database.init();

        var newStudent = new StudentEntity(
                "lgandre45@gmail.com",
                "password123",
                "Andreas",
                "Lumban Gaol",
                Gender.MALE,
                Religion.PROTESTANT,
                "221401067",
                Major.COMPUTER_SCIENCE,
                0.0
        );

        AccountService.registerStudent(repo, newStudent);

        var isLoginSuccessful = AccountService.login(
                repo,
                "lgandre45@gmail.com",
                "password123"
        );

        System.out.printf("Login successful: %s\n", isLoginSuccessful);



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
