package entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Listing {

    private Integer id;
    private String title;
    private String descr;
    private Long price;
    private Integer year;


}
