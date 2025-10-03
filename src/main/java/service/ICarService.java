package service;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICarService {
    List<CarResponseDto> getAllCars();

    CarResponseDto getCarById(Integer id);

    CarResponseDto addCar(CarRequestDto car);

    void deleteCar(Integer id);
}
