package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.PasswordUpdateRequest;
import com.aadim.project.dto.request.UserRegistrationRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController  extends BaseController {
    private final UserService userService;

    @PostMapping("/save")
@PreAuthorize("hasAuthority('ADMIN')")
public ResponseEntity<GlobalApiResponse> save(@RequestBody UserRegistrationRequest userRegistrationRequest) {
    UserRequest userRequest = new UserRequest(
            userRegistrationRequest.getFullName(),
            userRegistrationRequest.getEmail(),
            userRegistrationRequest.getContactNum(),
            userRegistrationRequest.getRoleId()
    );
    LoginRequest loginRequest = new LoginRequest(
            userRegistrationRequest.getUsername(),
            userRegistrationRequest.getPassword()
    );

    return successResponse(userService.saveUser(userRequest, loginRequest), "User created successfully.");
}


    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GlobalApiResponse> getAll () {
            return successResponse(userService.getAllUsers());
    }



    @GetMapping("/fetch/{id}")
    public ResponseEntity<GlobalApiResponse> getById(@PathVariable Integer id) {
        return successResponse(userService.getById(id));
    }


    @GetMapping("/getAllTeachers")
    public ResponseEntity<GlobalApiResponse> getAllTeachers () {
        return successResponse(userService.getAllTeachers());
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<GlobalApiResponse> getAllStudents () {
        return successResponse(userService.getAllStudents());
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse>  updateUsers(@RequestBody UserUpdateRequest request) {
        return successResponse(userService.updateUser(request), "User updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteStudent(@PathVariable Integer id) {
        return successResponse(userService.deleteStudent(id), "User deleted successfully.");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<GlobalApiResponse> updatePassword(@RequestBody PasswordUpdateRequest request) {
        return successResponse(userService.updatePassword(request), "Password updated successfully.");
    }

}
