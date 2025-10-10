package service.impl;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import entities.Brand;
import entities.Car;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BrandRepository;
import repository.CarRepository;
import service.ICarService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    private CarResponseDto convertToResponseDto(Car car){
        CarResponseDto dto = new CarResponseDto();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setPrice(car.getPrice());
        dto.setKm(car.getKm());
        dto.setColor(car.getColor());
        dto.setBrandName(car.getBrand().getName()); // marka ismini set et
        return dto;
    }


    @Override
    public List<CarResponseDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponseDto getCarById(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car not found with id:" + id));
        return convertToResponseDto(car);
    }

    //add update method

    @Override
    public CarResponseDto addCar(CarRequestDto carRequest) {
        // Önce brand tut
        Optional<Brand> brandOpt = brandRepository.findById(carRequest.getBrandId());
        if (brandOpt.isEmpty()) {
           throw new RuntimeException("Brand not found with id: " + carRequest.getBrandId());
        }

        Car car = new Car();
        car.setModel(carRequest.getModel());
        car.setYear(carRequest.getYear());
        car.setPrice(carRequest.getPrice());
        car.setKm(carRequest.getKm());
        car.setColor(carRequest.getColor());
        car.setBrand(brandOpt.get());

        Car saved = carRepository.save(car);
        return convertToResponseDto(saved);
    }

    @Override
    public void deleteCar(Integer id) {
        if (!carRepository.existsById(id)){
            throw new ResourceNotFoundException("Car not found with id:" + id);
        }
        carRepository.deleteById(id);
    }
}
