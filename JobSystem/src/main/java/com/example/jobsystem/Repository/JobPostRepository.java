package com.example.jobsystem.Repository;

import com.example.jobsystem.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost,Integer> {
}
