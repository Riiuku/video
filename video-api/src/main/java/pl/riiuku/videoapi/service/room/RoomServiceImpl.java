package pl.riiuku.videoapi.service.room;

import org.springframework.stereotype.Service;
import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.api.RoomResponse;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.repository.RoomRepository;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(RoomResponse::new).collect(Collectors.toList());
    }

    @Override
    public RoomResponse createNewRoom(RoomRequest roomRequest) {
        StringBuilder name = new StringBuilder(roomRequest.name);
        List<String> savedNames = roomRepository.findAllNamesLike(name.toString());
        if (savedNames.size() != 0 && savedNames.contains(name.toString())) {
            int numberName = 0;
            do {
                numberName++;
            } while (savedNames.contains(name + "_" + numberName));
            name.append("_").append(numberName);
        }
        return new RoomResponse(
                roomRepository.save(
                        new Room(
                                UUID.randomUUID(),
                                name.toString(),
                                roomRequest.maxSize,
                                LocalDateTime.now().plus(30, MINUTES))));
    }


}
