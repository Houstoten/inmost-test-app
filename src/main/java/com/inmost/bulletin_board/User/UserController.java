package com.inmost.bulletin_board.User;

import com.inmost.bulletin_board.Bulletin.dto.BulletinCreateDto;
import com.inmost.bulletin_board.User.dto.UserEditDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public String loadProfile(Model model) {
        model.addAttribute("user", userService.getUserToEditByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ));
        model.addAttribute("bulletin", new BulletinCreateDto());
        return "profile";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid UserEditDto dto,
                           BindingResult result) {
        if (!passwordEncoder.matches(dto.getPassword()
                , userService
                        .loadUserByUsername(
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getName()
                        ).getPassword()
        )
        ) {
            result.rejectValue("password"
                    , "400"
                    , "Passwords don`t match");
        } else {
            dto.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }

        if (result.hasErrors()) {
            return "redirect:/profile";
        }

        dto.setId(userService.getUserDtoByEmail(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()).getId());
        userService.editUser(dto);
        return "redirect:/";
    }

}
