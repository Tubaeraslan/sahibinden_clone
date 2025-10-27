package web.controller.impl;

import dto.requestDto.UserRequestDto;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repository.UserRepository;
import service.IUserService;
import service.impl.UserServiceImpl;
import web.controller.ILoginWebController;

import java.util.List;

@Controller
public class LoginWebControllerImpl implements ILoginWebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

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
    //add findByUserName
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

    //register sayfasını aç
    @GetMapping("/register")
    @Override
    public String showRegisterForm(Model model) {
        model.addAttribute("userRequest", new UserRequestDto());
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    @Override
    public String registerUser(@ModelAttribute("userRequest") UserRequestDto userRequest, Model model) {
        try {
            userService.addUser(userRequest);
            // kayıt başarılı -> login sayfasına yönlendir
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
