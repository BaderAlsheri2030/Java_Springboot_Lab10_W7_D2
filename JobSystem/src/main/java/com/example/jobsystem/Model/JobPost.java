package com.example.jobsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title cannot be null")
    @Size(min = 5, message = "title must be more than 4 characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String title;
    @NotNull(message = "Description cannot be null")
    @Column(columnDefinition = "varchar(50) not null")
    private String description;
    @NotNull(message = "location cannot be null")
    @Column(columnDefinition = "varchar(15) not null")
    private String location;
    @NotNull(message = "Salary cannot be null")
    @Positive(message = "salary must be a positive number")
    private Double salary;
    @Column(columnDefinition = "Date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date postingDate;

}
