package dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {

    private String model;
    private Integer year;
    private Double price;
    private Integer km;
    private String color;
    private Integer brandId;   //-car-brand- bağlantısı için lazım
}
