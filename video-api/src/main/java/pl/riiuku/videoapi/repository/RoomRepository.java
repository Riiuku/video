package pl.riiuku.videoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.riiuku.videoapi.domain.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT name FROM Room WHERE name = ?1 OR name like concat(?1, '_%')")
    List<String> findAllNamesLike(String name);
}
