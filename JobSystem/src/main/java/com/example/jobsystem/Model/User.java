package com.example.jobsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @Size(min = 5, message = "name must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "email must be valid")
    @Column(columnDefinition = "varchar(20) unique")
    private String email;

    @NotNull(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String password;

    @NotNull(message = "age cannot be null")
    @Min(value = 22, message = "age must be more than 21")
    @Digits(integer = 2,fraction = 0,message = "age must be only numbers")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotNull(message = "role cannot be null")
    @Column(columnDefinition = "varchar(10) check(role = 'JOB_SEEKER' or role = 'EMPLOYER')")
    private String role;



}
