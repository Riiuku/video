package pl.riiuku.videoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoCallSocketMessage {
    private UUID roomId;
    private byte[] videoBytes;
}
