package pl.riiuku.videoapi.controller;


import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import pl.riiuku.videoapi.service.socket.VideoSocketService;

import java.util.Objects;
import java.util.UUID;

@Controller
public class VideoSocketController {


    private final VideoSocketService videoSocketService;

    public VideoSocketController(VideoSocketService videoSocketService) {
        this.videoSocketService = videoSocketService;
    }

    @MessageMapping("/video-call")
    public void sendVideoBytes(byte[] message, @Header("room_id") String roomId, @Header("user_id") String userId) {
        videoSocketService.sendToRoomUsers(
                message,
                UUID.fromString(roomId),
                UUID.fromString(userId));

    }

}
