package entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//ilerde veritabanı ile birleştir
@Entity
public class User {

    private Integer id;
    private String userName;
    private String email;
    private String password;

    public User(Integer id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
