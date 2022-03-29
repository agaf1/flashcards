package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Card;

public class ResultOfLearn {

    private Card card;
    private boolean result;

    private ResultOfLearn(boolean result, Card card){
        this.card = card;
        this.result = result;
    }

    public Card getCard() {
        return card;
    }

    public boolean isResult() {
        return result;
    }

    static ResultOfLearn fail(Card card){
        return new ResultOfLearn(false,card);
    }

    static ResultOfLearn success(Card card){
        return new ResultOfLearn(true,card);
    }
}
