package web.controller;

import dto.requestDto.UserRequestDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public interface ILoginWebController {
    public String showLoginPage();
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model);
    String showRegisterForm(Model model);
    String registerUser(@ModelAttribute("userRequest") UserRequestDto userRequest, Model model);
}
