package pl.aga.flashcards.repository;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "basic_word", length = 100)
    private String basicWord;

    @Column (name = "translated_word", length = 100)
    private String translatedWord;

    @Column (name = "id_box")
    private int idBox;

    @Column (name="bucket", length = 10)
    private int bucket=1;

    @Column (name="iteration", length = 10)
    private int iteration;

    public void setBasicWord(String basicWord) {
        this.basicWord = basicWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }

    public int getBucket() {
        return bucket;
    }

    public void setBucket(int bucket) {
        this.bucket = bucket;
    }

    public String getBasicWord() {
        return basicWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public int getId() {
        return id;
    }

    public int getIdBox() {
        return idBox;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
