package dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class CarRequestDto {

    @NotEmpty(message = "Model cannot be empty")
    private String model;
    @NotEmpty(message = "Year cannot be empty")
    private Integer year;
    @NotEmpty(message = "Price cannot be empty")
    private Double price;
    @NotEmpty(message = "Km cannot be empty")
    private Integer km;
    @NotEmpty(message = "Color cannot be empty")
    private String color;
    @NotEmpty(message = "Brand id cannot be empty")
    private Integer brandId;   //-car-brand- bağlantısı için lazım
}
