package web.controller.impl;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repository.UserRepository;
import web.controller.ILoginWebController;

import java.util.List;

@Controller
public class LoginWebControllerImpl implements ILoginWebController {

    private UserRepository userRepository;

    @Autowired
    public LoginWebControllerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    @Override
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    @Override
    public String loginUser(String username, String password, Model model) {
        List<User> users = userRepository.findAll(); // Tüm kullanıcıları getir

        User matchedUser = null;
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                matchedUser = user;
                break;
            }
        }

        if (matchedUser != null) {
            return "redirect:/"; // başarılı giriş
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
