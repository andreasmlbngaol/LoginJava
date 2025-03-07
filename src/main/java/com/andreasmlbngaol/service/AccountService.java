package com.andreasmlbngaol.service;

import com.andreasmlbngaol.dto.User;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.entity.TeacherEntity;
import com.andreasmlbngaol.repository.DatabaseRepository;
import com.andreasmlbngaol.utils.PasswordManager;

public class AccountService {
    public static User currentUser = null;

    public static boolean login(DatabaseRepository repo, String email, String password) {
        var user = repo.getUserByEmail(email);
        if (user == null) { return false; }

        var isLoginSuccess = PasswordManager.verifyPassword(
                password,
                user.getPasswordHash()
        );

        if(isLoginSuccess) {
            currentUser = UserConverter.toDTO(user);
        }

        return isLoginSuccess;
    }

    public static void registerStudent(DatabaseRepository repo, StudentEntity newStudent) {
        var user = repo.getUserByEmail(newStudent.getEmail());
        if (user != null) {
            System.err.println("Error: Email already in use: " + newStudent.getEmail());
            return;
        }

        repo.insertStudent(newStudent);
    }

    public static void registerTeacher(DatabaseRepository repo, TeacherEntity newTeacher) {
        var user = repo.getUserByEmail(newTeacher.getEmail());
        if (user != null) {
            System.err.println("Error: Email already in use: " + newTeacher.getEmail());
            return;
        }

        repo.insertTeacher(newTeacher);
    }

    public static void logout() {
        currentUser = null;
    }
}
