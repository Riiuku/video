package pl.riiuku.videoapi.service.user;

import pl.riiuku.videoapi.api.UserRequest;
import pl.riiuku.videoapi.api.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUser(UUID publicId);
}
