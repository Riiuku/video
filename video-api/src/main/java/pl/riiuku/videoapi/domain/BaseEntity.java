package pl.riiuku.videoapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    public BaseEntity(UUID publicId) {
        this.publicId = publicId;
    }

    public BaseEntity() {
    }
}
