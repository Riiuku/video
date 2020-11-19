package pl.riiuku.videoapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.riiuku.videoapi.api.UserRequest;
import pl.riiuku.videoapi.api.UserResponse;
import pl.riiuku.videoapi.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
