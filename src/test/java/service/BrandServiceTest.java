package service;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import starter.SahibindenCloneApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SahibindenCloneApplication.class)
public class BrandServiceTest {

    @Autowired
    private IBrandService brandService;

    // GET testi
    @Test
    void testGetBrandById_ReturnsCorrectBrand_WhenIdExists() {
        BrandResponseDto brand = brandService.getBrandById(1);
        assertEquals("Dacia", brand.getName());
    }

    // NOT FOUND testi
    @Test
    void testGetBrandById_ThrowsException_WhenNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> brandService.getBrandById(9999));
    }

    // CREATE testi
    @Test
    void testCreateBrand_SavesAndReturnsBrand() {
        BrandRequestDto dto = new BrandRequestDto();
        dto.setName("Deneme");

        BrandResponseDto saved = brandService.addBrand(dto);

        assertNotNull(saved.getId());
        assertEquals("Deneme", saved.getName());
    }

    // UPDATE testi
    @Test
    void testUpdateBrand_ChangesNameSuccessfully() {
        BrandRequestDto dto = new BrandRequestDto();
        dto.setName("Renault");

        BrandResponseDto created = brandService.addBrand(dto);

        dto.setName("Renault Sport");
        BrandResponseDto updated = brandService.updateBrand(created.getId(), dto);

        assertEquals("Renault Sport", updated.getName());
    }

    // DELETE testi
    @Test
    void testDeleteBrand_RemovesBrandSuccessfully() {
        BrandRequestDto dto = new BrandRequestDto();
        dto.setName("Mazda");

        BrandResponseDto saved = brandService.addBrand(dto);

        brandService.deleteBrand(saved.getId());

        assertThrows(ResourceNotFoundException.class, () -> brandService.getBrandById(saved.getId()));
    }
}
