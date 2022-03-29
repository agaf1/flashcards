package pl.aga.flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.JPARepo;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private JPARepo jpaRepo;

    public List<Box> loadAll(){
        return jpaRepo.loadAll();
    }
}
