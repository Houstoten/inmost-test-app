package com.inmost.bulletin_board.Board;

import com.inmost.bulletin_board.Bulletin.BulletinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    private final BulletinService bulletinService;

    public BoardController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping
    public String loadBoard(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "10") Integer size
            , Model model) {
        model.addAttribute("bulletins", bulletinService.getBulletins(page, size));
        return "index";
    }
}
