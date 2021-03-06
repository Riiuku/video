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
@Table(name = "rooms")
@AllArgsConstructor
public class Room extends BaseEntity {


    @Column(name = "name", nullable = false)
    @Length(min = 4, max = 64)
    private String name;
    @Column(name = "max_size", nullable = true)
    private Integer maxSize;


    public Room(UUID publicId, @Length(min = 4, max = 64) String name, Integer maxSize, LocalDateTime createDate) {
        super(publicId, createDate);
        this.name = name;
        this.maxSize = maxSize;
    }

    public Room() {

    }
}
