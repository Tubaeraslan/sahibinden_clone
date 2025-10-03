package dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {

    private Integer id;
    private String model;
    private Integer year;
    private Double price;
    private Integer km;
    private String color;
    //private String brandName;  car-brand kullanıcıya sadece markayı ver id ye gerek yok

}
