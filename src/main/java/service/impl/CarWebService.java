package service.impl;

import entities.Car;
import exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarRepository;

import java.util.List;
@Service
public class CarWebService {
    private final CarRepository carRepository;

    @Autowired
    public CarWebService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            if (cars == null) return List.of();
            // Lazy load kontrolü
            cars.forEach(car -> {
                if (car.getBrand() != null) car.getBrand().getName();
            });
            return cars;
        } catch (Exception e) {
            throw new BadRequestException("Error fetching cars: " + e.getMessage());
        }
    }
}
