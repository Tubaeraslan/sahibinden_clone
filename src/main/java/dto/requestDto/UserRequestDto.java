package dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
//kullanıcının api ye gönderdiği veri
public class UserRequestDto {
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
