package pl.riiuku.videoapi.api;

import pl.riiuku.videoapi.domain.Room;

import java.time.LocalDateTime;
import java.util.UUID;

public class RoomResponse {
    public String name;
    public UUID publicId;
    public LocalDateTime lifeTime;
    public Integer maxSize;

    public RoomResponse(Room room) {
        this.name = room.getName();
        this.publicId = room.getPublicId();
        this.lifeTime = room.getCreateDate();
        this.maxSize = room.getMaxSize();
    }

}
