package pl.riiuku.videoapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    public BaseEntity(UUID publicId, LocalDateTime createDate) {
        this.publicId = publicId;
        this.createDate = createDate;
    }

    public BaseEntity() {
    }
}
