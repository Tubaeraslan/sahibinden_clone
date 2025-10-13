package controller.impl;

import controller.ICarController;
import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<CarResponseDto> getAllCars(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        return carService.getAllCars(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public CarResponseDto getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @Override
    @PostMapping
    public CarResponseDto addCar(@Valid @RequestBody CarRequestDto car) {
        return carService.addCar(car);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
    }
}
