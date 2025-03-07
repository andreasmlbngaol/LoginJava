package com.andreasmlbngaol.dto;

import com.andreasmlbngaol.enums.Gender;
import com.andreasmlbngaol.enums.Religion;
import com.andreasmlbngaol.enums.Role;

public class User {
    protected final long id;
    protected final String email;
    protected final String firstName;
    protected final String lastName;
    protected final Role role;
    protected final Gender gender;
    protected final Religion religion;

    public User(long id, String email, String firstName, String lastName, Role role, Gender gender, Religion religion) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Role getRole() {
        return role;
    }

    public Gender getGender() {
        return gender;
    }

    public long getId() {
        return id;
    }

    public Religion getReligion() {
        return religion;
    }

    @Override
    public String toString() {
        return String.format(
                "User(id=%d, email=\"%s\", firstName=\"%s\", lastName=\"%s\", role=\"%s\", gender=\"%s\", religion=\"%s\")",
                id, email, firstName, lastName, role, gender, religion
        );
    }
}
