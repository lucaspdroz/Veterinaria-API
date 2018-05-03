package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final String NOMEPU= "VeterinariaAPIPU";
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory(NOMEPU);
    
    public static EntityManager createManager(){
        return factory.createEntityManager();
    }
}
