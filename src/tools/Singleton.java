
package tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
    private static Singleton instance;
    private EntityManager em;
    
    private Singleton(){
        init();
    }
    
    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopPU");
        em = emf.createEntityManager();
    }
    
    public EntityManager getEntityManager(){
        return em;
    }
}
