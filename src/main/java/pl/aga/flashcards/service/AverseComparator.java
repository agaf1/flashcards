package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Card;

import java.util.function.BiPredicate;

public class AverseComparator implements BiPredicate<Card, Card> {
    @Override
    public boolean test(Card cardWithAnswer, Card actualCard) {
        return cardWithAnswer.getTranslatedWord().equalsIgnoreCase(actualCard.getTranslatedWord());
    }
}
