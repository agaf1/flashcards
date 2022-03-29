package pl.aga.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.aga.flashcards.controller.dto.CardDTO;
import pl.aga.flashcards.controller.dto.MapperDTO;
import pl.aga.flashcards.service.CardService;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private MapperDTO mapperDTO;

    @GetMapping (path = "/box/{id}")
    public String showBox (@PathVariable int id, Model model){
        model.addAttribute("cards", cardService.loadAllCardsByBox(id));
        return "card";
    }

    @GetMapping (path = "/box/{id}/card/add")
    public String addCard(@PathVariable int id,Model model){
        model.addAttribute("boxId",id);
        return "addCard";
    }

    @PostMapping (path = "/box/card/create")
    public RedirectView createCard( @ModelAttribute CardDTO cardDTO){
        cardService.save(cardDTO);
        return  new RedirectView( "/box/"+cardDTO.getBoxId());
    }
}
