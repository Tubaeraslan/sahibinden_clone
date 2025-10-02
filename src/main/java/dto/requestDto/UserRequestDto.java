package dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//kullanıcının api ye gönderdiği veri
public class UserRequestDto {
    private String userName;
    private String email;
    private String password;
}
