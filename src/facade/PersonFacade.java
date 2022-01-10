package facade;

import classes.Person;
import javax.persistence.EntityManager;
import tools.Singleton;

public class PersonFacade extends AbstractFacade<Person>{
    
    private EntityManager em;

    public PersonFacade(Class<Person> entityClass) {
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
