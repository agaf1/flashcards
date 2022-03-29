package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Card;

class CardFactory {
    public Card from(NewCard cardDetails){
        Card card = new Card();
        card.setBasicWord(cardDetails.getBasicWord());
        card.setTranslatedWord(cardDetails.getTranslatedWord());
        card.setIdBox(cardDetails.getBoxId());
        card.setBucket(1);
        card.setIteration(0);
        return card;
    }
 }
