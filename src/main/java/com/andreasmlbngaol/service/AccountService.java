package com.andreasmlbngaol.service;

import com.andreasmlbngaol.dto.User;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.entity.TeacherEntity;
import com.andreasmlbngaol.repository.Repository;
import com.andreasmlbngaol.util.PasswordManager;

public class AccountService {
    public static User currenUser = null;

    public static boolean login(String email, String password, Repository repo) {
        var user = repo.getUserByEmail(email);
        if (user == null) { return false; }

        var isLoginSuccess = PasswordManager.verifyPassword(
                password,
                user.getPasswordHash()
        );

        if(isLoginSuccess) {
            currenUser = UserConverter.toDTO(user);
        }

        return isLoginSuccess;
    }

    public static void registerStudent(StudentEntity newStudent, Repository repo) {
        var user = repo.getUserByEmail(newStudent.getEmail());

        if (user != null) {
            System.err.println("Error: Email already in use: " + newStudent.getEmail());
            return;
        }

        repo.insertStudent(newStudent);
    }

    public static void registerTeacher(TeacherEntity newTeacher, Repository repo) {
        var user = repo.getUserByEmail(newTeacher.getEmail());
        if (user != null) {
            System.err.println("Error: Email already in use: " + newTeacher.getEmail());
            return;
        }

        repo.insertTeacher(newTeacher);
    }
}
