package pl.riiuku.videoapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Room extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Length(min = 4, max = 64)
    private String name;
    @Column(name = "max_size", nullable = true)
    private Long maxSize;
}
