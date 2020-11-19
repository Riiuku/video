package pl.riiuku.videoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.riiuku.videoapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
