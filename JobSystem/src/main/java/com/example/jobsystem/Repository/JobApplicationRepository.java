package com.example.jobsystem.Repository;

import com.example.jobsystem.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {
}
