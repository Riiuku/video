package pl.riiuku.videoapi.service.room;

import pl.riiuku.videoapi.domain.Room;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RoomSchedulerService {
    LocalDateTime extentTimeOfRoomLife(UUID publicId);

    void createNewDeleteTask(Room room);
}
