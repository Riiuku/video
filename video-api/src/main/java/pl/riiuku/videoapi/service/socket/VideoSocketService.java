package pl.riiuku.videoapi.service.socket;

import java.util.UUID;

public interface VideoSocketService {
    void sendToRoomUsers(byte[] video, UUID roomPublicId, UUID userPublicId);
}
