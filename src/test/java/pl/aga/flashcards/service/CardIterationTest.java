package pl.aga.flashcards.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardIterationTest {

    private Box box;
    private List<Card> cards;

    @BeforeEach
    public void setUp(){
        box = new Box();
        box.setName("animals");
        box.setDayOfLearn(1);
        cards = new ArrayList<>();
        Builder builder = new Builder(box.getId());
        cards.add(builder.basicWord("kot").translatedWord("cat").build());
        cards.add(builder.basicWord("pies").translatedWord("dog").build());
        cards.add(builder.basicWord("mysz").translatedWord("mouse").build());
    }

    @Test
    public void shouldTakeCardsFromBucket1() throws EmptyBoxException {
        CardIteration cardIteration = new CardIteration(box,cards);
        Card card = cardIteration.nextCard();
        Assertions.assertEquals(1,card.getId());
    }

    @Test
    public void shouldBeException() throws EmptyBoxException {
        CardIteration cardIteration = new CardIteration(box,new ArrayList<>());
        Assertions.assertThrows(EmptyBoxException.class, () -> cardIteration.nextCard());
    }

    @Test
    public void shouldTakeCardsFromBucket5() throws EmptyBoxException{
        cards.get(0).setBucket(5);
        cards.get(1).setBucket(5);
        cards.get(2).setBucket(5);
        CardIteration cardIteration = new CardIteration(box,cards);
        Card card = cardIteration.nextCard();

        Assertions.assertEquals(1,card.getId());
        Assertions.assertEquals(5,card.getBucket());
        Assertions.assertEquals(5,box.getDayOfLearn());
    }

    public static class Builder{
        private int boxId;
        private int cardId = 1;
        private String basicWord;
        private String translatedWord;
        private int bucket = 1;
        private int iteration = 0;

        public Builder(int boxId){
            this.boxId = boxId;
        }
        public Builder basicWord(String word){
            basicWord = word;
            return this;
        }
        public Builder translatedWord(String word){
            translatedWord = word;
            return this;
        }
        public Builder bucket(int val){
            bucket = val;
            return this;
        }
        public Builder iteration(int val){
            iteration = val;
            return this;
        }
        public Card build(){
            Card card = new Card();
            card.setId(cardId);
            cardId++;
            return card;
        }
    }
}