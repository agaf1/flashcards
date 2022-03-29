package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CardIteration {
    private Box box;
    private List<Card> cards;

    CardIteration(Box box, List<Card> cards) {
        this.box = box;
        this.cards = cards;
    }

    public Card nextCard() throws EmptyBoxException {
        if (cards.size() == 0) {
            throw new EmptyBoxException();
        }
        Card cardToLearn = null;

        int day = box.getDayOfLearn();
        for (; day < box.getDayOfLearn() + 5; day++) {
            cardToLearn = cardToLearn(day);
            if (cardToLearn != null) {
                break;
            }
        }
        box.setDayOfLearn(day);
        return cardToLearn;
    }

    private Card cardToLearn(int dayOfLearn) {
        List<Card> cardsToLearn = cardsByBucket(1, dayOfLearn);
        List<Card> cardsFromBucket;
        if ((dayOfLearn % 2) == 0 && (dayOfLearn % 4) != 0) {
            cardsFromBucket = cardsByBucket(2, dayOfLearn);
        } else if ((dayOfLearn % 3) == 0) {
            cardsFromBucket = cardsByBucket(3, dayOfLearn);
        } else if ((dayOfLearn % 4) == 0) {
            cardsFromBucket = new ArrayList<>();
            cardsFromBucket.addAll(cardsByBucket(2, dayOfLearn));
            cardsFromBucket.addAll(cardsByBucket(4, dayOfLearn));
        } else if ((dayOfLearn % 5) == 0) {
            cardsFromBucket = cardsByBucket(5, dayOfLearn);
        } else {
            cardsFromBucket = Collections.emptyList();
        }

        for (Card c : cardsFromBucket) {
            cardsToLearn.add(c);
        }

        return cardsToLearn.size() > 0 ? cardsToLearn.get(0) : null;
    }

    private List<Card> cardsByBucket(int bucket, int iteration) {
        return cards.stream()
                .filter(card -> (bucket == card.getBucket() && iteration != card.getIteration()))
                .collect(Collectors.toList());
    }
}
