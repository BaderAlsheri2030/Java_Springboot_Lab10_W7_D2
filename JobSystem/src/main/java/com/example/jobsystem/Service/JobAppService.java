package com.example.jobsystem.Service;

import com.example.jobsystem.Model.JobApplication;
import com.example.jobsystem.Model.JobPost;
import com.example.jobsystem.Model.User;
import com.example.jobsystem.Repository.JobApplicationRepository;
import com.example.jobsystem.Repository.JobPostRepository;
import com.example.jobsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class JobAppService {
    private final JobApplicationRepository repository;
    private final JobPostRepository postService;
    private final UserRepository userService;

    public List<JobApplication> getApplications(){
        return repository.findAll();
    }

    public void apply(JobApplication application){

        for (JobPost post : postService.findAll()) {
            if (post.getId() == application.getJobPostId()){
                application.setJobPostId(post.getId());
            }
        }
        for (User user : userService.findAll()) {
            if (user.getId() == application.getUserId()){
                application.setId(user.getId());
            }
        }

        repository.save(application);
    }
    public char withdraw(Integer id){
        JobApplication application = repository.getById(id);
        for (JobApplication application1: repository.findAll()) {
            if (application1.getId() == id){
                repository.delete(application);
                return 'A';
            }
        }
        return 'F';
    }

}
