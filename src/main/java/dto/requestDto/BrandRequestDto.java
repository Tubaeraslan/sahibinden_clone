package dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class BrandRequestDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
