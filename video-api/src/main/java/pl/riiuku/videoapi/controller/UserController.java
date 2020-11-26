package pl.riiuku.videoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.riiuku.videoapi.api.UserResponse;
import pl.riiuku.videoapi.service.user.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{publicId}")
    public UserResponse getUser(@PathVariable UUID publicId) {
        return userService.getUser(publicId);
    }
}
