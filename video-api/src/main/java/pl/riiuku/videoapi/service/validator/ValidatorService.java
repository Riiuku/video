package pl.riiuku.videoapi.service.validator;

import java.util.UUID;

public interface ValidatorService {
    void socketHandshakeValidator(UUID roomId, UUID userId);
}
