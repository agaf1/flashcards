package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Box;

class BoxFactory {

    public Box create (String name){
        Box box = new Box();
        box.setName(name);
        box.setDayOfLearn(1);
        return box;
    }

}
