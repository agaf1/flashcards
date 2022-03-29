package pl.aga.flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aga.flashcards.repository.Card;
import pl.aga.flashcards.repository.JPARepo;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private JPARepo jpaRepo;

    public List<Card> loadAllCardsByBox(int boxId){
        return jpaRepo.loadAllCardsByBox(boxId);
    }

    public void save(NewCard card){
        CardFactory cardFactory = new CardFactory();
        jpaRepo.save(cardFactory.from(card));
    }
}
