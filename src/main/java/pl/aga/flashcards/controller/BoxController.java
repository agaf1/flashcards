package pl.aga.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.aga.flashcards.controller.dto.BoxDTO;
import pl.aga.flashcards.controller.dto.MapperDTO;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.service.BoxService;

@Controller
public class BoxController {

    @Autowired
    private MapperDTO mapperDTO;

    @Autowired
    private HomeController homeController;

    @Autowired
    private BoxService boxService;

    @GetMapping(path = "/box/add")
    public String addBox() {
        return "addBox";
    }

    @PostMapping(path = "/box/create")
    public RedirectView create(@ModelAttribute BoxDTO boxDTO) {
        boxService.create(boxDTO.getName());
        return new RedirectView("/");
    }

    @GetMapping(path = "/box/{id}/confirm-delete")
    public String confirmDelete(@PathVariable int id, Model model) {
        model.addAttribute("boxId", id);
        return "deleteBox";
    }

    @GetMapping(path = "/box/{id}/delete")
    public RedirectView delete(@PathVariable int id) {
        boxService.delete(id);
        return new RedirectView("/");
    }

    @GetMapping(path = "/box/{id}/rename")
    public String renameForm(@PathVariable int id, Model model) {
        Box box = boxService.findById(id);
        if (box != null) {
            model.addAttribute("boxId", box.getId());
            model.addAttribute("name", box.getName());
            model.addAttribute("existsBox", true);
        } else {
            model.addAttribute("existsBox", false);
        }
        return "renameBox";
    }

    @PostMapping(path = "/box/{id}/rename/now")
    public RedirectView rename(@ModelAttribute BoxDTO boxDTO, @PathVariable int id) {
        boxService.rename(id, boxDTO.getName());
        return new RedirectView("/");
    }
}
