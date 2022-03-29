package pl.aga.flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;
import pl.aga.flashcards.repository.JPARepo;

import java.util.List;
import java.util.function.BiPredicate;

@Service

public class LearnService {

    @Autowired
    private JPARepo jpaRepo;

    public Card takeNextCard(int boxId) throws  EmptyBoxException{
        Box box = jpaRepo.findById(boxId);
        List<Card>  cards = jpaRepo.loadAllCardsByBox(boxId);
        CardIteration learn = new CardIteration(box,cards);
        Card  card = learn.nextCard();
        jpaRepo.save(box);
        return card;
    }

    public ResultOfLearn checkCorrectnessOfAnswer(int boxId, Card cardWithAnswer, BiPredicate<Card, Card> wordComparator) {
        Box box = jpaRepo.findById(boxId);
        Card actualCard = jpaRepo.findById(boxId,cardWithAnswer.getId());
        CardAnswerCheck cardAnswerCheck = new CardAnswerCheck(box,actualCard,cardWithAnswer);
        ResultOfLearn resultOfLearn = cardAnswerCheck.checkCorrectnessOfAnswer(wordComparator);
        actualCard = resultOfLearn.getCard();
        jpaRepo.save(actualCard);
        return resultOfLearn;
    }

    public Box findById(int boxId){
        return jpaRepo.findById(boxId);
    }
}
