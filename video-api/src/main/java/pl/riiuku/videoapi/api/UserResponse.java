package pl.riiuku.videoapi.api;

import pl.riiuku.videoapi.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponse {
    public UUID publicId;
    public String userName;
    public LocalDateTime createDate;

    public UserResponse(User user) {
        this.userName = user.getUserName();
        this.createDate = user.getCreateDate();
        this.publicId = user.getPublicId();
    }

    public UserResponse() {
    }
}
