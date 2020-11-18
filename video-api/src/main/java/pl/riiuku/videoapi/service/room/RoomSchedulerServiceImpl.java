package pl.riiuku.videoapi.service.room;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.repository.RoomRepository;

import java.util.UUID;
import java.util.concurrent.*;

@Service
public class RoomSchedulerServiceImpl implements RoomSchedulerService {

    private final RoomRepository roomRepository;
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static final ConcurrentHashMap<UUID, ScheduledFuture<?>> schedulers = new ConcurrentHashMap<>();

    public RoomSchedulerServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void extentTimeOfRoomLife(UUID publicId) {

    }

    @Override
    public void createNewDeleteTask(Room room)  {
        ScheduledFuture<?> scheduledFuture = executor.schedule(() -> {
            roomRepository.deleteById(room.getId());
            schedulers.remove(room.getPublicId());
        }, 30, TimeUnit.SECONDS);
        schedulers.put(room.getPublicId(), scheduledFuture);
    }
}
