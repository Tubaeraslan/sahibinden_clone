package service;


import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import starter.SahibindenCloneApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SahibindenCloneApplication.class)
public class CarServiceTest {

    @Autowired
    private ICarService carService ;

    //get testi
    @Test
    void testGetCarBrand_ReturnBMW_WhenIdIs1(){
        String brand = carService.getCarById(1).getBrandName();
        assertEquals("Mercedes",brand);
    }

    //null get testi
    @Test
    void testGetCarById_ThrowsException_WhenNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(9999));
    }

    //save testi
    @Test
    void testCreateCar_SavesAndReturnsCar() {
        CarRequestDto dto = new CarRequestDto();
        dto.setBrandId(2);
        dto.setModel("A3");
        dto.setYear(2020);

        CarResponseDto savedCar = carService.addCar(dto);

        assertEquals("Mercedes", savedCar.getBrandName());
        assertEquals(2020, savedCar.getYear());
    }

    //update testi
    @Test
    void testUpdateCar_ChangesBrandSuccessfully() {
        CarRequestDto dto = new CarRequestDto();
        dto.setBrandId(3);
        dto.setModel("X5");
        dto.setYear(2019);
        dto.setPrice(900000.0);
        dto.setColor("RED");
        dto.setKm(10000);

        CarResponseDto created = carService.addCar(dto);

        dto.setBrandId(4);
        CarResponseDto updated = carService.updateCar(created.getId(), dto);

        assertEquals("TOFAS", updated.getBrandName());
    }

    //delete testi
    @Test
    void testDeleteCar_RemovesCarSuccessfully() {
        CarRequestDto dto = new CarRequestDto();
        dto.setBrandId(5);
        dto.setModel("Corolla");
        dto.setYear(2022);

        CarResponseDto saved = carService.addCar(dto);

        carService.deleteCar(saved.getId());

        assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(saved.getId()));
    }

}
