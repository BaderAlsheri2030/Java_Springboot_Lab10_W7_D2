package com.example.jobsystem.Service;

import com.example.jobsystem.Model.User;
import com.example.jobsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getUsers(){
        return  repository.findAll();
    }

    public void addUser(User user){
        repository.save(user);
    }

    public Boolean updateUser(Integer id, User user){
        User user1 = repository.getById(id);
        if (user1 == null){
            return false;
        }
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        repository.save(user1);
        return true;

    }

    public char deleteUser(Integer id){
        User user1 = repository.getById(id);
        for (User user: repository.findAll()) {
            if (user.getId() == id){
                repository.delete(user1);
                return 'A';
            }
        }

        return 'F';
    }

}
