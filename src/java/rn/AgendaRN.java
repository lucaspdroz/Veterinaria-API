package rn;

import entidade.Agenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

public class AgendaRN {

    public AgendaRN() {

    }

    public Agenda inserir(Agenda agenda) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(agenda);
        manager.getTransaction().commit();

        manager.close();

        return (agenda);

    }

    public Agenda buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Agenda agenda = manager.find(Agenda.class, id);
        //Verificacao de id
        manager.close();
        return agenda;
    }

    public Agenda atualizar(Agenda agenda) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        agenda = manager.merge(agenda);
        manager.getTransaction().commit();

        manager.close();

        return (agenda);
    }

    public Agenda deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Agenda agenda = manager.find(Agenda.class, id);

        manager.getTransaction().begin();
        manager.remove(agenda);
        manager.getTransaction().commit();

        manager.close();

        return (agenda);

    }

    public List<Agenda> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Agenda m");
        List<Agenda> listaAgenda = query.getResultList();
        return listaAgenda;
    }

}
