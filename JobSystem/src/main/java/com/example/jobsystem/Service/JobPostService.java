package com.example.jobsystem.Service;

import com.example.jobsystem.Model.JobPost;
import com.example.jobsystem.Repository.JobPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobPostService {
    private final JobPostRepository repository;


    public List<JobPost> getPosts(){
        return repository.findAll();
    }

    public void addPost(JobPost post){
        repository.save(post);
    }

    public Boolean updatePost(Integer id, JobPost post){
        JobPost post1 = repository.getById(id);
        if (post1 == null){
            return false;
        }
        post1.setDescription(post.getDescription());
        post1.setLocation(post.getLocation());
        post1.setTitle(post.getTitle());
        post1.setSalary(post.getSalary());
        repository.save(post1);
        return true;
    }

    public char deletePost(Integer id){
        JobPost post = repository.getById(id);
        for (JobPost post1 : repository.findAll()) {
            if (post1.getId() == id){
                repository.delete(post);
                return 'A';
            }
        }
        return 'F';
    }

}
