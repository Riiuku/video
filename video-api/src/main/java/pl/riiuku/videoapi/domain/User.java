package pl.riiuku.videoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "user_name", nullable = false)
    @Length(min = 4, max = 64)
    private String userName;

    public User(UUID publicId, LocalDateTime createDate, @Length(min = 4, max = 64) String userName) {
        super(publicId, createDate);
        this.userName = userName;
    }

    public User() {

    }

}
