package com.inmost.bulletin_board.Bulletin;

import com.inmost.bulletin_board.Bulletin.dto.BulletinCreateDto;
import com.inmost.bulletin_board.User.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    private final BulletinService bulletinService;
    private final UserService userService;

    public BulletinController(BulletinService bulletinService, UserService userService) {
        this.bulletinService = bulletinService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String createBulletin(@ModelAttribute("bulletin") @Valid BulletinCreateDto dto,
                                      BindingResult result){
//        dto.setUserDto(user.getFirstName().isBlank() ? user.getEmail() : user.getFirstName());
        dto.setUserDto(userService.getUserDtoByEmail(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        bulletinService.addBulletin(dto);
        return "redirect:/";
    }
}
