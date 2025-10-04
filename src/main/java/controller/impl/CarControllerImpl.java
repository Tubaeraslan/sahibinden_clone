package controller.impl;

import controller.ICarController;
import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ICarService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/cars")
public class CarControllerImpl implements ICarController {

    @Autowired
    private ICarService carService;

    @Override
    @GetMapping
    public List<CarResponseDto> getAllCars() {
        return carService.getAllCars();
    }

    @Override
    @GetMapping("/{id}")
    public CarResponseDto getCarById(Integer id) {
        return carService.getCarById(id);
    }

    @Override
    @PostMapping
    public CarResponseDto addCar(@Valid @RequestBody CarRequestDto car) {
        return carService.addCar(car);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCar(Integer id) {
        carService.deleteCar(id);
    }
}
