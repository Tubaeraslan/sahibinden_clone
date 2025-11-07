package dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

public class CarRequestDto {

    @NotEmpty(message = "Model cannot be empty")
    private String model;
    @NotNull(message = "Year cannot be empty")
    private Integer year;
    @NotNull(message = "Price cannot be empty")
    private Double price;
    @NotNull(message = "Km cannot be empty")
    private Integer km;
    @NotEmpty(message = "Color cannot be empty")
    private String color;
    @NotNull(message = "Brand id cannot be empty")
    private Integer brandId;   //-car-brand- bağlantısı için lazım

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
