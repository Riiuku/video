package pl.riiuku.videoapi.service.room;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.exception.RoomNotFoundException;
import pl.riiuku.videoapi.repository.RoomRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class RoomSchedulerServiceImpl implements RoomSchedulerService {

    // TODO What should happening when instance went down?
    private final RoomRepository roomRepository;
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static final ConcurrentHashMap<UUID, ScheduledFuture<?>> schedulers = new ConcurrentHashMap<>();

    public RoomSchedulerServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public LocalDateTime extentTimeOfRoomLife(UUID publicId) {
        return roomRepository.findByPublicId(publicId).map(room -> {
            room.setCreateDate(LocalDateTime.now().plus(30, ChronoUnit.SECONDS));

            if (schedulers.containsKey(publicId)) {
                schedulers.get(publicId).cancel(true);
                createNewDeleteTask(room);
            }

            return roomRepository.save(room).getCreateDate();
        }).orElseThrow(() -> new RoomNotFoundException("Room not found!"));
    }

    @Override
    public void createNewDeleteTask(Room room) {
        schedulers.remove(room.getPublicId());
        ScheduledFuture<?> scheduledFuture = executor.schedule(() -> {
            roomRepository.deleteById(room.getId());
            schedulers.remove(room.getPublicId());
        }, 30, TimeUnit.SECONDS);
        schedulers.put(room.getPublicId(), scheduledFuture);
    }
}
