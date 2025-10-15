package web.controller.impl;

import controller.ICarController;
import entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.controller.ICarWebController;
import web.service.ICarWebService;
import web.service.impl.CarWebServiceImpl;

import java.util.List;

@Controller
public class CarWebControllerImpl implements ICarWebController {
    private final ICarWebService carWebService;

    @Autowired
    public CarWebControllerImpl(CarWebServiceImpl carWebService) {
        this.carWebService = carWebService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Car> cars = carWebService.getAllCars();
        if (cars == null) cars = List.of(); // Boş liste kontrolü
        model.addAttribute("cars", cars);
        return "index"; // templates/index.html render
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        // Güvenli liste kontrolü
        List<Car> cars = carWebService.getAllCars();
        if (cars == null) {
            cars = List.of(); // Boş liste
        }
        model.addAttribute("cars", cars);
        return "cars"; // templates/cars.html render
    }

}
