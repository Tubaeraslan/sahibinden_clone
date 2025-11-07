package dto.requestDto;

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
