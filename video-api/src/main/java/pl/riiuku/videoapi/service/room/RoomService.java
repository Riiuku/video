package pl.riiuku.videoapi.service.room;

import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.domain.Room;

import java.util.List;
import java.util.Set;

public interface RoomService {
    List<Room> getAllRooms();
    Room createNewRoom(RoomRequest roomRequest);
}
