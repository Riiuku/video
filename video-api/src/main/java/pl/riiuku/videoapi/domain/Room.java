package pl.riiuku.videoapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "room")
public class Room extends BaseEntity {


    @Column(name = "name", nullable = false)
    @Length(min = 4, max = 64)
    private String name;
    @Column(name = "max_size", nullable = true)
    private Integer maxSize;

    public Room(@Length(min = 4, max = 64) String name, Integer maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }


    public Room() {

    }
}
