package pl.riiuku.videoapi.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class VideoSocketController {

    @MessageMapping("/video-call")
    @SendTo("/topic/video-call")
    public String sendVideoBytes(String message) {
        return message + ", String test";
    }

}
