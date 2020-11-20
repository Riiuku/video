package pl.riiuku.videoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.riiuku.videoapi.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPublicId(UUID publicId);
}
