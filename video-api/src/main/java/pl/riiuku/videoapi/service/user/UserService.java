package pl.riiuku.videoapi.service.user;

import pl.riiuku.videoapi.api.UserRequest;
import pl.riiuku.videoapi.api.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
}
