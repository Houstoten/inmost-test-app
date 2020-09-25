package com.inmost.bulletin_board.Auth;

import com.inmost.bulletin_board.User.UserService;
import com.inmost.bulletin_board.User.dto.UserRegisterDto;
import lombok.SneakyThrows;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("user")
    public UserRegisterDto userRegistrationDto() {
        return new UserRegisterDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    @SneakyThrows
    public String register(@ModelAttribute("user") @Valid UserRegisterDto dto,
                           BindingResult result) {

        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            result.rejectValue("confirmPassword"
                    , "400"
                    , "Passwords don`t match");
        }

        try {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            userService.saveUser(dto);
        } catch (Exception e) {
            result.rejectValue("email"
                    , "400"
                    , "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        return "redirect:/login";
    }

}
