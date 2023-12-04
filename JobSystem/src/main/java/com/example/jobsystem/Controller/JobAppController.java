package com.example.jobsystem.Controller;

import com.example.jobsystem.ApiResponse.ApiResponse;
import com.example.jobsystem.Model.JobApplication;
import com.example.jobsystem.Service.JobAppService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job")
@AllArgsConstructor
public class JobAppController {

    private final JobAppService service;


    @GetMapping("/get")
    public ResponseEntity getApplications(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplications());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAplication(@Valid @RequestBody JobApplication application, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        service.apply(application);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Application created"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> withdrawApplication(@PathVariable Integer id) {
        char isDeleted = service.withdraw(id);
        if (isDeleted == 'A'){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Application withdrawn"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid job Application or Application doesn't exist"));

    }
}
