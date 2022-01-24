package facade;

import classes.Sale;
import javax.persistence.EntityManager;
import tools.Singleton;


public class SaleFacade extends AbstractFacade<Sale>{
    private EntityManager em;

    public SaleFacade(Class<Sale> entityClass) {
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
