package pl.riiuku.videoapi.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pl.riiuku.videoapi.domain.VideoCallSocketMessage;

@Controller
public class VideoSocketController {

    @MessageMapping("/video-call")
    @SendTo("/topic/video-call")
    public byte[] sendVideoBytes(byte[] message) {
        System.out.println("Wchodzi do metody");
        return message;

    }

}
