package pl.aga.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.aga.flashcards.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(path = "/")
    public String star(Model m) {
        m.addAttribute("boxes", homeService.loadAll());
        return "home";
    }
}
