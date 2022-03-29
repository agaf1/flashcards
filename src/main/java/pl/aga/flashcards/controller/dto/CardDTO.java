package pl.aga.flashcards.controller.dto;

import pl.aga.flashcards.service.NewCard;

public class CardDTO implements NewCard {
    private int boxId;
    private String basicWord;
    private String translatedWord;

    @Override
    public String getBasicWord() {
        return basicWord;
    }

    public void setBasicWord(String basicWord) {
        this.basicWord = basicWord;
    }

    @Override
    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    @Override
    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }
}
