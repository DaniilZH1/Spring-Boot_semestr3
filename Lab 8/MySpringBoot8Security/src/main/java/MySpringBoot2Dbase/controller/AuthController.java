package MySpringBoot2Dbase.controller;

import MySpringBoot2Dbase.dto.UserRegistrationDto;
import MySpringBoot2Dbase.entity.User;
import MySpringBoot2Dbase.service.UserServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) { this.userService = userService; }

    @GetMapping({"/", "/index"})
    public String index() { return "index"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/register")
    public String registration(Model model) {
        if (!model.containsAttribute("user")) model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserRegistrationDto dto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) return "register";
        if (!userService.register(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword())) {
            model.addAttribute("emailExists", true);
            return "register";
        }
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserSummary> users = userService.findAll().stream()
                .map(user -> new UserSummary(user.getFirstName(), user.getLastName(), user.getEmail()))
                .toList();
        model.addAttribute("users", users);
        return "users";
    }

    public record UserSummary(String firstName, String lastName, String email) { }
}
