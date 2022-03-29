package pl.aga.flashcards.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JPARepo {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Box box){
        entityManager.persist(box);
    }
    @Transactional
    public void delete(Box box){
        entityManager.createQuery("delete from Card where idBox =?1")
                        .setParameter(1,box.getId())
                                .executeUpdate();
        entityManager.remove(box);
    }

    public List<Box> loadAll(){
        return entityManager.createQuery("select b from Box b").getResultList();
    }

    public Box findById(int id){
        return entityManager.find(Box.class,id);
    }

    public List<Card> loadAllCardsByBox(int idBox){
        return entityManager
                .createQuery("select c from Card c where c.idBox = ?1")
                .setParameter(1,idBox)
                .getResultList();
    }


    @Transactional
    public void save(Card card){
        entityManager.persist(card);
    }

    @Transactional
    public Card findById (int boxId, int cardId){
        return (Card) entityManager
                .createQuery("select c from Card c where c.idBox = ?1 and c.id=?2")
                .setParameter(1,boxId)
                .setParameter(2,cardId)
                .getSingleResult();

    }
}
