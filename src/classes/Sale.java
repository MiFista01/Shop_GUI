package classes;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Person person;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Shoes shoes;

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", person=" + person + ", shoes=" + shoes + '}';
    }
    
}
