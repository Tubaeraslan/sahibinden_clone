package service;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import org.springframework.data.domain.Page;

public interface ICarService {
    Page<CarResponseDto> getAllCars(Integer page, Integer size);

    CarResponseDto getCarById(Integer id);

    CarResponseDto addCar(CarRequestDto car);

    CarResponseDto updateCar(Integer id, CarRequestDto carRequest);

    void deleteCar(Integer id);
}
