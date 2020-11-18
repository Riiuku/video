package pl.riiuku.videoapi.service.room;

import pl.riiuku.videoapi.domain.Room;

import java.util.UUID;

public interface RoomSchedulerService {
    void extentTimeOfRoomLife(UUID publicId);

    void createNewDeleteTask(Room room);
}
