package com.tweetapp.tweet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Email
    private String email;

    @NotNull
    private String firstName;

    private String lastName;

    @NotNull
    private String gender;

    private Date dob;

    @NotNull
    private String password;

}
