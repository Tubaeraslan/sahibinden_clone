package web.controller;

import org.springframework.ui.Model;

public interface ICarWebController {
    String getCars(String query,Model model);
}