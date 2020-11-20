package pl.riiuku.videoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.domain.RoomUser;
import pl.riiuku.videoapi.domain.User;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
    boolean existsByUserAndRoom(User user, Room room);
}
