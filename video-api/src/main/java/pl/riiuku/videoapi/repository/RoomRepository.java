package pl.riiuku.videoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.riiuku.videoapi.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT name FROM Room WHERE name = ?1 OR name like concat(?1, '_%')")
    List<String> findAllNamesLike(String name);
    Optional<Room> findByPublicId(UUID publicId);
}
