package dto.requestDto;

import jakarta.validation.constraints.NotEmpty;


//kullanıcının api ye gönderdiği veri
public class UserRequestDto {
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
