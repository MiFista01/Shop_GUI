package facade;

import classes.Shoes;
import javax.persistence.EntityManager;
import tools.Singleton;

public class ShoesFacade extends AbstractFacade<Shoes>{
    
    private EntityManager em;

    public ShoesFacade(Class<Shoes> entityClass) {
        super(entityClass);
        init();
    }
    
    private void init(){
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
