package pl.riiuku.videoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.service.room.RoomService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public List<Room> getAvailableRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room saveRoom(@RequestBody @Valid RoomRequest roomRequest) {
        return roomService.createNewRoom(roomRequest);
    }
}
