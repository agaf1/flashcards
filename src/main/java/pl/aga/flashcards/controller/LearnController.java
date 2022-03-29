package pl.aga.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.aga.flashcards.controller.dto.CardLearnDTO;
import pl.aga.flashcards.controller.dto.MapperDTO;
import pl.aga.flashcards.repository.Card;
import pl.aga.flashcards.service.*;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static pl.aga.flashcards.controller.dto.Display.AVERS;


@Controller
public class LearnController {

    @Autowired
    private LearnService learnService;
    @Autowired
    private MapperDTO mapperDTO;

    @GetMapping(path = "/box/{id}/learning/{display}")
    public String toLearn(@PathVariable int id, @PathVariable String display, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("dayOfLearn",learnService.findById(id).getDayOfLearn());
        try {
            Card card = learnService.takeNextCard(id);
            model.addAttribute("cardId", card.getId());
            model.addAttribute("display", display);
            String question;
            if (display.equals(AVERS)) {
                question = card.getBasicWord();
            } else {
                question = card.getTranslatedWord();
            }
            model.addAttribute("question", question);

            return "learnForm";
        } catch (EmptyBoxException e) {
            return "learnException";
        }
    }


    @PostMapping(path = "/box/{id}/learning/check")
    public String checkAnswer(@PathVariable int id, @ModelAttribute CardLearnDTO cardLearnDTO, Model model) {

        String display = cardLearnDTO.getDisplay();

        BiPredicate<Card, Card> wordComparator = getWordComparator(cardLearnDTO);

        Card cardWithAnswer = mapperDTO.map(cardLearnDTO);
        ResultOfLearn resultOfLearn = learnService.checkCorrectnessOfAnswer(id, cardWithAnswer, wordComparator);

        model.addAttribute("display", display);

        if (resultOfLearn.isResult() == true) {
            return buildCorrectAnswerView();
        } else {
            return buildIncorrectAnswerView(cardLearnDTO.getDisplay(), resultOfLearn, model);
        }
    }
    
    private BiPredicate<Card, Card> getWordComparator(CardLearnDTO cardLearnDTO) {
        String display = cardLearnDTO.getDisplay();
        BiPredicate<Card, Card> wordComparator;
        if (display.equals(AVERS)) {
            wordComparator = new AverseComparator();
        } else {
            wordComparator = new ReverseComparator();
        }
        return wordComparator;
    }

    private String buildCorrectAnswerView() {
        return "learnCorrect";
    }

    private String buildIncorrectAnswerView(String display, ResultOfLearn resultOfLearn, Model model) {
        Supplier<String> correctWord;
        if (display.equals(AVERS)) {
            correctWord = resultOfLearn.getCard()::getTranslatedWord;
        } else {
            correctWord = resultOfLearn.getCard()::getBasicWord;
        }
        model.addAttribute("translatedWord", correctWord.get());
        return "learnFail";
    }


}
