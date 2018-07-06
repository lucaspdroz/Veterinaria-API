package rn;

import entidade.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class AnimalRN {

    public AnimalRN() {

    }

    public Animal inserir(Animal animal) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(animal);
        manager.getTransaction().commit();

        manager.close();

        return (animal);

    }

    public Animal buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Animal animal = manager.find(Animal.class, id);
        //Verificacao de id
        manager.close();
        return animal;
    }

    public Animal atualizar(Animal animal) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        animal = manager.merge(animal);
        manager.getTransaction().commit();

        manager.close();

        return (animal);
    }

    public Animal deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Animal animal = manager.find(Animal.class, id);

        manager.getTransaction().begin();
        manager.remove(animal);
        manager.getTransaction().commit();

        manager.close();

        return (animal);

    }

    public List<Animal> listar() {  
        
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Animal m");
        List<Animal> listaAnimais = query.getResultList();
        manager.close();
        return listaAnimais;
    }

}
