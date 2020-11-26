package pl.riiuku.videoapi.service.user;

import org.springframework.stereotype.Service;
import pl.riiuku.videoapi.domain.User;
import pl.riiuku.videoapi.api.UserRequest;
import pl.riiuku.videoapi.api.UserResponse;
import pl.riiuku.videoapi.exception.UserNotFoundException;
import pl.riiuku.videoapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return new UserResponse(
                userRepository.save(
                        new User(
                                UUID.randomUUID(),
                                LocalDateTime.now(),
                                userRequest.userName)));
    }

    @Override
    public UserResponse getUser(UUID publicId) {
        User user = userRepository.findByPublicId(publicId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserResponse(user);
    }
}
