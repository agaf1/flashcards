package pl.aga.flashcards.repository;

import javax.persistence.*;

@Entity
@Table(name = "boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",length = 30)
    private String name;

    @Column(name="day_of_learn")
    private int dayOfLearn;

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDayOfLearn() {
        return dayOfLearn;
    }

    public void setDayOfLearn(int dayOfLearn) {
        this.dayOfLearn = dayOfLearn;
    }
}
