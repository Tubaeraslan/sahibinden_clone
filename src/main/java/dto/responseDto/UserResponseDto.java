package dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//api den istemciye dönen değer
public class UserResponseDto {
    private Integer id;
    private String userName;
    private String email;
}
