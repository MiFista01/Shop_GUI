package shop;

import classes.Person;
import classes.Purchase;
import classes.Shoes;
import facade.PersonFacade;
import facade.PurchaseFacade;
import facade.ShoesFacade;
import java.util.Scanner;

public class App {
    private Scanner scanner = new Scanner(System.in);
    boolean on = true;
    
    private PersonFacade personFacade;
    private ShoesFacade shoesFacade;
    private PurchaseFacade purchaseFacade;

    public App() {
        personFacade = new PersonFacade(Person.class);
        shoesFacade = new ShoesFacade(Shoes.class);
        purchaseFacade = new PurchaseFacade(Purchase.class);
    }
    
    
    public void run() {
        
        while(on){
        System.out.println("1) Redister new account");
        System.out.println("2) Log in account");
        System.out.print("Your choise - ");
        int choice = Int_input();
        switch(choice){
            case 1:
                add_person();
                break;
            }
        }
    }
    private int Int_input(){
        boolean test = true;
        int check = 0;
        while(test){
            try{
                String numb = scanner.next();
                check = Integer.parseInt(numb);
                test = false;
                }
            catch (Exception e){
                System.out.println("You wrote letters");
                System.out.println("Please try again");
                test = false;
                }
            }
        return check;
        }
    private void add_person(){
        Person person = new Person();
        System.out.print("Write your name - ");
        person.setName(scanner.next());
        System.out.print("Write your password - ");
        person.setPassword(scanner.next());
        System.out.print("Write your phone - ");
        person.setPhone(scanner.next());
        personFacade.create(person);
        }
}
