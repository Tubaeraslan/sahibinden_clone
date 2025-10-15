package dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;


public class BrandRequestDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
