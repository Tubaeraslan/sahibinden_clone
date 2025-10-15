package controller.impl;

import entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.CarWebService;

import java.util.List;

@Controller
public class CarWebController {
    private final CarWebService carWebService;

    @Autowired
    public CarWebController(CarWebService carWebService) {
        this.carWebService = carWebService;
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
