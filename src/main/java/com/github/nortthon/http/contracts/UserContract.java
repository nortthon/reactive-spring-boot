package com.github.nortthon.http.contracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserContract {

    private String name;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
