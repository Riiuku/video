package pl.riiuku.videoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "rooms_users")
@AllArgsConstructor
public class RoomUser extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("room_id")
    private Room room;

    public RoomUser() {

    }

    public RoomUser(UUID publicId, LocalDateTime createDate, User user, Room room) {
        super(publicId, createDate);
        this.user = user;
        this.room = room;
    }
}