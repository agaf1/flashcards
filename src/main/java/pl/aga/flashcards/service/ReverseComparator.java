package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Card;

import java.util.function.BiPredicate;

public class ReverseComparator implements BiPredicate<Card,Card> {

    @Override
    public boolean test(Card cardWithAnswer, Card actualCard) {
        return cardWithAnswer.getBasicWord().equalsIgnoreCase(actualCard.getBasicWord());
    }
}
