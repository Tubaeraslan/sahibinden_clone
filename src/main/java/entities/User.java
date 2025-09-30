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


}
