package com.example.jobsystem.Controller;

import com.example.jobsystem.ApiResponse.ApiResponse;
import com.example.jobsystem.Model.JobPost;
import com.example.jobsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
public class JobPostController {
    private final JobPostService service;

    @GetMapping("/get")
    public ResponseEntity getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPost(@Valid @RequestBody JobPost post, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        service.addPost(post);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Integer id,@Valid @RequestBody JobPost post,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        Boolean isUpdated = service.updatePost(id,post);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post Added"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid ID or post doesn't exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
        char isDeleted = service.deletePost(id);
        if (isDeleted == 'A'){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid ID or Post doesn't exist"));
    }

}
