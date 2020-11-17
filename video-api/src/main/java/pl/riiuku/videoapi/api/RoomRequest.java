package pl.riiuku.videoapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {
    @Length(min = 4, max = 64)
    @NotNull
    public String name;

    public Integer maxSize;
}
