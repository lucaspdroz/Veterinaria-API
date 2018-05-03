package rn;

import entidade.Responsavel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;


public class ResponsavelRN {

    public ResponsavelRN() {

    }

    public Responsavel inserir(Responsavel responsavel) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(responsavel);
        manager.getTransaction().commit();

        manager.close();

        return (responsavel);

    }

    public Responsavel buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Responsavel responsavel = manager.find(Responsavel.class, id);
        //Verificacao de id
        manager.close();
        return responsavel;
    }

    public Responsavel atualizar(Responsavel responsavel) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        responsavel = manager.merge(responsavel);
        manager.getTransaction().commit();

        manager.close();

        return (responsavel);
    }

    public Responsavel deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Responsavel responsavel = manager.find(Responsavel.class, id);

        manager.getTransaction().begin();
        manager.remove(responsavel);
        manager.getTransaction().commit();

        manager.close();

        return (responsavel);

    }

    public List<Responsavel> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Responsavel m");
        List<Responsavel> listaResponsavel = query.getResultList();
        return listaResponsavel;
    }

}
