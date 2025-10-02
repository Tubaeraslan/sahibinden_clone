package entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Brand {

    private Integer id;
    private String name;

    public Brand(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
