package pl.riiuku.videoapi.service.socket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VideoSocketServiceImpl implements VideoSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public VideoSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendToRoomUsers(byte[] video, UUID roomPublicId, UUID userPublicId) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user_id", userPublicId);
        this.simpMessagingTemplate
                .convertAndSend("/topic/video-call/" + roomPublicId, video, headers);
    }
}
