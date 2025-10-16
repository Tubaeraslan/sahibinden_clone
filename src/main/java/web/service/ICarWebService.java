package web.service;

import entities.Car;
import java.util.List;

public interface ICarWebService {
    List<Car> getAllCars();
    List<Car> searchCars(String query);
}