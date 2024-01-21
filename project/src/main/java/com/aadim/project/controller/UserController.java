package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.MailDto;
import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRegistrationRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController  extends BaseController {
    private final UserService userService;


    @PostMapping("/save")
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
        return successResponse(userService.saveUser(userRequest, loginRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAll ( UserResponse response) {
        return successResponse(userService.getAllPersons());
    }



    @GetMapping("/fetch/{id}")
    public ResponseEntity<GlobalApiResponse> getById(@PathVariable Integer id) {
        return successResponse(userService.getById(id));
    }


    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse>  updateUsers(@RequestBody UserUpdateRequest request) {
//        if (userService.updateUser(request) == )
        return successResponse(userService.updateUser(request));
    }

    @PostMapping("/send-mail-html")
    public ResponseEntity<GlobalApiResponse> sendMailWithHtml(@ModelAttribute MailDto mailDto) throws MessagingException {
        return successResponse("Mail is Processing!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteStudent(@PathVariable Integer id) {
        return successResponse(userService.deleteStudent(id));
    }

}
