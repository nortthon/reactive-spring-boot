package com.github.nortthon.http.contracts;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserContract {

    private String name;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
