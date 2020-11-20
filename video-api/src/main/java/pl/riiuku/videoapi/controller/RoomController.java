package pl.riiuku.videoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.api.RoomResponse;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.service.room.RoomSchedulerService;
import pl.riiuku.videoapi.service.room.RoomService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomSchedulerService roomSchedulerService;

    public RoomController(RoomService roomService, RoomSchedulerService roomSchedulerService) {
        this.roomService = roomService;
        this.roomSchedulerService = roomSchedulerService;
    }

    @GetMapping()
    public List<RoomResponse> getAvailableRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse saveRoom(@RequestBody @Valid RoomRequest roomRequest) {
        return roomService.createNewRoom(roomRequest);
    }

    @PatchMapping(path = "{publicId}/time")
    @ResponseStatus(HttpStatus.OK)
    public LocalDateTime extendRoomLifeTime(@PathVariable UUID publicId) {
        return roomSchedulerService.extentTimeOfRoomLife(publicId);
    }

    @PostMapping(path = "{publicId}/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinToRoom(@PathVariable(value = "publicId") UUID roomId, @Header("user_id") UUID userId) {
        roomService.joinToRoom(roomId, userId);
    }
}
