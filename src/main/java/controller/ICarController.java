package controller;

import dto.requestDto.CarRequestDto;
import dto.requestDto.UserRequestDto;
import dto.responseDto.CarResponseDto;
import dto.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICarController {

    Page<CarResponseDto> getAllCars(Integer page, Integer size);

    CarResponseDto getCarById(@PathVariable Integer id);

    CarResponseDto addCar(@RequestBody CarRequestDto car);

    CarResponseDto updateCar(@PathVariable Integer id, @RequestBody CarRequestDto carRequest);

    void deleteCar(@PathVariable Integer id);
}
