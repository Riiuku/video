package pl.riiuku.videoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.riiuku.videoapi.domain.Room;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @GetMapping()
    public Set<Room> getAvailableRooms() {
        return Collections.emptySet();
    }
}
