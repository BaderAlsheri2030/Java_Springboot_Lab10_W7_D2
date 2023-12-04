package com.example.jobsystem.Controller;

import com.example.jobsystem.ApiResponse.ApiResponse;
import com.example.jobsystem.Model.User;
import com.example.jobsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
private final UserService userService;

@GetMapping("/get")
public ResponseEntity getUsers(){
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
}

@PostMapping("/add")
public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    userService.addUser(user);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user added"));

}

@PutMapping("/update/{id}")
public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user,Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    boolean isUpdated = userService.updateUser(id,user);
    if (isUpdated){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user updated"));
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid id or user doesn't exist"));
}

@DeleteMapping("/delete/{id}")
public ResponseEntity deleteUser(@PathVariable Integer id){
    char isDeleted = userService.deleteUser(id);
    if (isDeleted == 'A'){
        return ResponseEntity.status(HttpStatus.OK).body("user is deleted");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid id or user doesn't exist"));
}


}
