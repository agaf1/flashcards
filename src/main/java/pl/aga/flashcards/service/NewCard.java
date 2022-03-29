package pl.aga.flashcards.service;

import javax.persistence.Column;

public interface NewCard {
    public int getBoxId();
    public String getBasicWord();
    public String getTranslatedWord();
}
