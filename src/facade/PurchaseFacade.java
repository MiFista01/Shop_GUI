package facade;

import classes.Purchase;
import javax.persistence.EntityManager;
import tools.Singleton;

public class PurchaseFacade extends AbstractFacade<Purchase>{
    
    private EntityManager em;

    public PurchaseFacade(Class<Purchase> entityClass) {
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
