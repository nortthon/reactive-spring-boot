package com.github.nortthon.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private LocalDate birthday;
}
