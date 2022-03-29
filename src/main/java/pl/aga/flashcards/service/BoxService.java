package pl.aga.flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.JPARepo;

@Service
public class BoxService {

    @Autowired
    private JPARepo jpaRepo;


    public void create(String name) {
        BoxFactory boxFactory = new BoxFactory();
        Box box = boxFactory.create(name);
        save(box);
    }

    public void rename(int id, String name) {
        Box box = findById(id);
        box.setName(name);
        save(box);
    }

    public void delete(int boxId) {
        jpaRepo.delete(findById(boxId));
    }

    public Box findById(int boxId) {
        return jpaRepo.findById(boxId);
    }

    private void save(Box box) {
        jpaRepo.save(box);
    }
}
