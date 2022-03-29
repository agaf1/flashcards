package pl.aga.flashcards.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;

class CardAnswerCheckTest {

    private Box box;
    private Card actualCard;
    private Card cardWithAnswer;

    @BeforeEach
    public void setUp(){
        box = new Box();
        box.setName("animals");
        box.setDayOfLearn(2);
        actualCard = new Card();
        actualCard.setId(1);
        actualCard.setIdBox(box.getId());
        actualCard.setBasicWord("kot");
        actualCard.setTranslatedWord("cat");
        actualCard.setBucket(2);
        actualCard.setIteration(1);
        cardWithAnswer = new Card();
        cardWithAnswer.setId(1);
        cardWithAnswer.setIdBox(box.getId());
        cardWithAnswer.setBasicWord("kot");
        cardWithAnswer.setTranslatedWord("cat");
        cardWithAnswer.setBucket(1);
        cardWithAnswer.setIteration(0);
    }

    @Test
    public void shouldBeCorrectAnswer1(){
        CardAnswerCheck cardAnswerCheck = new CardAnswerCheck(box,actualCard,cardWithAnswer);
        ResultOfLearn resultOfLearn = cardAnswerCheck.checkCorrectnessOfAnswer(new AverseComparator());

        Assertions.assertTrue(resultOfLearn.isResult());
        Assertions.assertEquals(3,resultOfLearn.getCard().getBucket());
        Assertions.assertEquals(2,resultOfLearn.getCard().getIteration());
    }

    @Test
    public void shouldBeCorrectAnswer2(){
        actualCard.setBucket(5);
        CardAnswerCheck cardAnswerCheck = new CardAnswerCheck(box,actualCard,cardWithAnswer);
        ResultOfLearn resultOfLearn = cardAnswerCheck.checkCorrectnessOfAnswer(new AverseComparator());

        Assertions.assertTrue(resultOfLearn.isResult());
        Assertions.assertEquals(5,resultOfLearn.getCard().getBucket());
        Assertions.assertEquals(2,resultOfLearn.getCard().getIteration());
    }

    @Test
    public void shouldBeFailAnswer(){
        cardWithAnswer.setTranslatedWord("dog");
        CardAnswerCheck cardAnswerCheck = new CardAnswerCheck(box,actualCard,cardWithAnswer);
        ResultOfLearn resultOfLearn = cardAnswerCheck.checkCorrectnessOfAnswer(new AverseComparator());

        Assertions.assertFalse(resultOfLearn.isResult());
        Assertions.assertEquals(1,resultOfLearn.getCard().getBucket());
        Assertions.assertEquals(2,resultOfLearn.getCard().getIteration());
        Assertions.assertEquals("cat",resultOfLearn.getCard().getTranslatedWord());
    }
}