package service;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICarService {
    Page<CarResponseDto> getAllCars(Integer page, Integer size);

    CarResponseDto getCarById(Integer id);

    CarResponseDto addCar(CarRequestDto car);

    void deleteCar(Integer id);
}
