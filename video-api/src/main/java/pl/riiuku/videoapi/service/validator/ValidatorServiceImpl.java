package pl.riiuku.videoapi.service.validator;

import org.springframework.stereotype.Service;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.domain.RoomUser;
import pl.riiuku.videoapi.domain.User;
import pl.riiuku.videoapi.exception.RoomNotFoundException;
import pl.riiuku.videoapi.exception.UserNotFoundException;
import pl.riiuku.videoapi.repository.RoomRepository;
import pl.riiuku.videoapi.repository.RoomUserRepository;
import pl.riiuku.videoapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;

    public ValidatorServiceImpl(UserRepository userRepository, RoomRepository roomRepository, RoomUserRepository roomUserRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.roomUserRepository = roomUserRepository;
    }

    @Override
    public void socketHandshakeValidator(UUID roomId, UUID userId) {
        Room room = roomRepository.findByPublicId(roomId).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        User user = userRepository.findByPublicId(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!roomUserRepository.existsByUserAndRoom(user, room))
            roomUserRepository.save(new RoomUser(UUID.randomUUID(), LocalDateTime.now(), user, room));
    }
}
