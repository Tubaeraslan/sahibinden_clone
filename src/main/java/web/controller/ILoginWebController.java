package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface ILoginWebController {
    public String showLoginPage();
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model);
}
