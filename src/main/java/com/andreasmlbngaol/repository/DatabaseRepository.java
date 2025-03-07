package com.andreasmlbngaol.repository;

import com.andreasmlbngaol.db.Database;
import com.andreasmlbngaol.entity.StudentEntity;
import com.andreasmlbngaol.entity.TeacherEntity;
import com.andreasmlbngaol.entity.UserEntity;
import com.andreasmlbngaol.enums.Role;

import java.util.List;

public class DatabaseRepository {
    public List<UserEntity> getAllUsers() {
        return Database.executeTransaction(session ->
                session.createQuery("from UserEntity", UserEntity.class)
                        .list()
        );
    }

    public List<UserEntity> getUsersByRole(Role role) {
        return Database.executeTransaction( session ->
                session.createQuery("from UserEntity where role = :role", UserEntity.class)
                        .setParameter("role", role)
                        .list()
        );
    }

    public UserEntity getUserById(long id) {
        return Database.executeTransaction(session ->
                session.find(UserEntity.class, id)
        );
    }

    public UserEntity getUserByEmail(String email) {
        return Database.executeTransaction( session ->
                session.createQuery("from UserEntity where email = :email", UserEntity.class)
                        .setParameter("email", email)
                        .uniqueResult()
        );
    }

    public StudentEntity getStudentByStudentId(String studentId) {
        return Database.executeTransaction(session ->
                session.createQuery("from StudentEntity where studentId = :studentId", StudentEntity.class)
                        .setParameter("studentId", studentId)
                        .uniqueResult()
        );
    }

    public TeacherEntity getTeacherByTeacherId(String teacherId) {
        return Database.executeTransaction(session ->
                session.createQuery("from TeacherEntity where teacherId = :teacherId", TeacherEntity.class)
                        .setParameter("teacherId", teacherId)
                        .uniqueResult()
        );
    }

    public void insertStudent(StudentEntity newStudent) {
        Database.executeTransactionVoid(session ->
                session.persist(newStudent)
        );
    }

    public void insertTeacher(TeacherEntity newTeacher) {
        Database.executeTransactionVoid(session ->
                session.persist(newTeacher)
        );
    }
}
