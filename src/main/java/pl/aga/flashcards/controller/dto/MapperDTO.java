package pl.aga.flashcards.controller.dto;

import org.springframework.stereotype.Component;
import pl.aga.flashcards.controller.dto.BoxDTO;
import pl.aga.flashcards.controller.dto.CardDTO;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;

@Component
public class MapperDTO {

    public Card map(CardLearnDTO cardLearnDTO){
        Card card = new Card();
        card.setId(cardLearnDTO.getCardId());
        card.setIdBox(cardLearnDTO.getBoxId());
        card.setBucket(1);
        card.setIteration(0);
        if(cardLearnDTO.getDisplay().equals("avers")) {
            card.setBasicWord(cardLearnDTO.getQuestion());
            card.setTranslatedWord(cardLearnDTO.getAnswer());
        }else{
            card.setTranslatedWord(cardLearnDTO.getQuestion());
            card.setBasicWord(cardLearnDTO.getAnswer());

        }

        return card;
    }

}
