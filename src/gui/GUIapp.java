package gui;


import classes.Person;
import classes.Purchase;
import classes.Sale;
import classes.Shoes;
import facade.PersonFacade;
import facade.PurchaseFacade;
import facade.SaleFacade;
import facade.ShoesFacade;
import gui.components.button;
import gui.components.editor;
import gui.components.label;
import gui.components.list_purchase;
import gui.components.list_sale;
import gui.profile.profile_label;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUIapp extends JFrame{
    public static int w = 400;
    public static int h = 200;
    
    PersonFacade personFacade = new PersonFacade(Person.class);
    ShoesFacade shoesFacade = new ShoesFacade(Shoes.class);
    PurchaseFacade purchaseFacade = new PurchaseFacade(Purchase.class);
    SaleFacade saleFacade = new SaleFacade (Sale.class);
    
    JPanel panel = new JPanel();
    JTabbedPane tabs = new JTabbedPane();
    JPanel profile = new JPanel();
    JPanel purchases = new JPanel();
    JPanel sells = new JPanel();
    JPanel shopping = new JPanel();
    JPanel advert = new JPanel();
    
    label program_topic = new label(w,50,"Shop of shoes",1,15);
    label warning = new label(w,20,"You don't have some information",1,10);
    button author;
    button reg;
    static GUIapp gui =  new GUIapp();
    
    public static Long id;
    
    list_sale list_purchases = new list_sale(w+200,"aaa",w,300);
    list_purchase my_purchases = new list_purchase(w+200,"aaa",w,300);
    
    public GUIapp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.setSize(w, h);
        this.setResizable(false);
        this.setTitle("Shop");
        
        author = new button("Log In",w/2-50,50);
        author.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                w = 600;
                h = 400;
                warning.setVisible(false);
                GUIapp.this.setSize(w,h);
                panel.removeAll();
                editor name = new editor(w,20,"Name:",1,15,w/3-10,20,w/3*2-5,20);
                editor password = new editor(w/3,20,"Password:",1,15,w/3-10,20,w/3*2-5,20);
                
                button authorize = new button("authorize",100,20);
                authorize.getButton().addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (name.getText().getText().equals("")){
                                warning.setVisible(true);
                        }
                        if (password.getText().getText().equals("")){
                            warning.setVisible(true);
                        }
                        if ((!name.getText().getText().equals("")) && (!password.getText().getText().equals(""))){
                            List<Person> persons = personFacade.findAll();
                            for(int i = 0; i <persons.size(); i++){
                                if (persons.get(i).getName().equals(name.getText().getText())){
                                    if(persons.get(i).getPassword().equals(password.getText().getText())){
                                        id = persons.get(i).getId();
                                        PROFILE();
                                        break;
                                    }
                                }
                            }

                        }
                    }
                });
                
                panel.add(name);
                panel.add(password);
                panel.add(warning);
                JPanel pan = new JPanel();
                pan.add(MAIN());
                pan.add(authorize);
                panel.add(pan);
                }
        });
        reg = new button("Registration",w/2-50,50);
        reg.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                w = 600;
                h = 400;
                GUIapp.this.setSize(w,h);
                panel.removeAll();
                editor editor1 = new editor(w,20,"Name:",1,15,w/3-10,20,w/3*2-5,20);
                editor editor2 = new editor(w/3,20,"Password:",1,15,w/3-10,20,w/3*2-5,20);
                editor editor3 = new editor(w/3,20,"Phone:",1,15,w/3-10,20,w/3*2-5,20);
                warning.setVisible(false);
                button registration = new button("Register",100,20);
                registration.getButton().addActionListener(new ActionListener(){
                    @Override
                        public void actionPerformed(ActionEvent e) {
                            if (editor1.getText().getText().equals("")){
                                warning.setVisible(true);
                            }
                            if (editor2.getText().getText().equals("")){
                                warning.setVisible(true);
                            }
                            if (editor3.getText().getText().equals("")){
                                warning.setVisible(true);
                            }
                            if ((!editor1.getText().getText().equals("")) && (!editor2.getText().getText().equals("")) && 
                            (!editor3.getText().getText().equals(""))){
                                Person person = new Person();
                                person.setName(editor1.getText().getText());
                                person.setPassword(editor2.getText().getText());
                                person.setPhone(editor3.getText().getText());
                                personFacade.create(person);
                                editor1.getText().setText("");
                                editor2.getText().setText("");
                                editor3.getText().setText("");
                                warning.setVisible(false);
                            }
                        }
                    });
        
                panel.add(editor1);
                panel.add(editor2);
                panel.add(editor3);
                panel.add(warning);
                panel.add(registration);
                panel.add(MAIN());
                
            }
        });
        
        
        panel.add(program_topic);
        panel.add(reg);
        panel.add(author);
        this.add(panel);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               gui.setVisible(true);
            }
        });
    }
    
    
    public button MAIN(){
            button exit = new button("Exit",100,20);
            exit.getButton().addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e) {
                        w = 400;
                        h = 200;
                        GUIapp.this.setSize(w,h);
                        panel.removeAll();
                        panel.add(program_topic);
                        panel.add(reg);
                        panel.add(author);
                    }
                });
            return exit;
        }
    private void PROFILE(){
        label my_money = new label(w,30,"Money: "+personFacade.findById(id).getPurse(),1,15);
        
        w = 700;
        h = 500;
        GUIapp.this.setSize(w,h);
        panel.removeAll();
        tabs.setPreferredSize(new Dimension(w, h));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        panel.add(tabs);

        tabs.add("Profile", profile);
            JPanel Info = new JPanel();
            Info.setSize(w/2, h);
            profile.add(Info);
            profile_label profile_name = new profile_label(w-40,30,"Name: "+personFacade.findById(id).getName(),1,20);
            profile_label profile_phone = new profile_label(w-40,30,"Phone: "+personFacade.findById(id).getPhone(),1,20);
            profile_label profile_purse = new profile_label(w-40,30,"Purse: "+personFacade.findById(id).getPurse(),1,20);
            profile_label profile_income = new profile_label(w-40,30,"Income: "+personFacade.findById(id).getIncome(),1,20);
            profile.add(profile_name);
            profile.add(profile_phone);
            profile.add(profile_purse);
            profile.add(profile_income);
            button add_money = new button("Add money", w, 30);
            add_money.getButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    w = 200;
                    h = 200;
                    GUIapp.this.setSize(w,h);
                    panel.removeAll();
                    
                    Person pers = personFacade.findById(id);
                    editor add_money = new editor(w,20,"Money:",1,15,w/3,20,w/3*2,20);
                    
                    button back = new button("Back",w,30);
                    back.getButton().addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tabs.removeAll();
                            panel.removeAll();
                            profile.removeAll();
                            shopping.removeAll();
                            sells.removeAll();
                            purchases.removeAll();
                            advert.removeAll();
                            PROFILE();
                        }
                    });
                    button money = new button("Add money",w,30);
                    money.getButton().addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            double numb = 0;
                            try{
                                numb = Double.parseDouble(add_money.getText().getText());
                            }
                            catch(Exception ea){
                                numb = 0;
                            }
                            pers.setPurse(pers.getPurse()+numb);
                            personFacade.edit(pers);
                            add_money.getText().setText("");
                        }
                    });
                    panel.add(add_money);
                    panel.add(back);
                    panel.add(money);
                }
            });
            button log_out = new button("log out", w, 30);
            log_out.getButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    w = 400;
                    h = 200;
                    GUIapp.this.setSize(w,h);
                    tabs.removeAll();
                    panel.removeAll();
                    profile.removeAll();
                    shopping.removeAll();
                    sells.removeAll();
                    purchases.removeAll();
                    advert.removeAll();
                    panel.add(program_topic);
                    panel.add(reg);
                    panel.add(author);
                }
            });
            profile.add(add_money);
            profile.add(log_out);
            
            
            tabs.add("My purchases", shopping);
            shopping.add(my_purchases);
            
            
            tabs.add("Sell product",sells);
            label war1 = new label(w,20,"You didn't write firma's name",1,10);
            label war2 = new label(w,20,"You didn't write shoes color",1,10);
            label war3 = new label(w,20,"You didn't write model",1,10);
            label war4 = new label(w,20,"You didn't write prise",1,10);
            label war5 = new label(w,20,"You didn't write size",1,10);
            label war6 = new label(w,20,"You didn't write count",1,10);
            label war7 = new label(w,30,"Wrong data",1,15);
            war1.setVisible(false);
            war2.setVisible(false);
            war3.setVisible(false);
            war4.setVisible(false);
            war5.setVisible(false);
            war6.setVisible(false);
            war7.setVisible(false);

            editor firma = new editor(w,15,"firma:",1,12,w/3,20,w/3*2,15);
            editor color = new editor(w,15,"color:",1,12,w/3,20,w/3*2,15);
            editor model = new editor(w,15,"model:",1,12,w/3,20,w/3*2,15);
            editor prise = new editor(w,15,"prise:",1,12,w/3,20,w/3*2,15);
            editor size = new editor(w,15,"size:",1,12,w/3,20,w/3*2,15);
            editor count = new editor(w,15,"count:",1,12,w/3,20,w/3*2,15);
            sells.add(firma);
            sells.add(war1);
            sells.add(color);
            sells.add(war2);
            sells.add(model);
            sells.add(war3);
            sells.add(prise);
            sells.add(war4);
            sells.add(size);
            sells.add(war5);
            sells.add(count);
            sells.add(war6);
            sells.add(war7);
            
            button clean = new button("Clean",100,30);
            clean.getButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    firma.getText().setText("");
                    color.getText().setText("");
                    model.getText().setText("");
                    prise.getText().setText("");
                    size.getText().setText("");
                    count.getText().setText("");
                    war1.setVisible(false);
                    war2.setVisible(false);
                    war3.setVisible(false);
                    war4.setVisible(false);
                    war5.setVisible(false);
                    war6.setVisible(false);
                    war7.setVisible(false);

                }

            });
            sells.add(clean);
            
            button button_sell = new button("Sell shoes",100,30);
            button_sell.getButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                     if(firma.getText().getText().equals("")){
                    war1.setVisible(true);
                    }
                    if(color.getText().getText().equals("")){
                        war2.setVisible(true);
                    }
                    if(model.getText().getText().equals("")){
                        war3.setVisible(true);
                    }
                    if(prise.getText().getText().equals("")){
                        war4.setVisible(true);
                    }
                    if(size.getText().getText().equals("")){
                        war5.setVisible(true);
                    }
                    if(count.getText().getText().equals("")){
                        war6.setVisible(true);
                    }
                    if((!firma.getText().getText().equals(""))&&(!color.getText().getText().equals(""))&&
                    (!model.getText().getText().equals(""))&&(!prise.getText().getText().equals(""))&&
                    (!size.getText().getText().equals(""))&&(!count.getText().getText().equals(""))){
                        try{
                            Shoes shoes = new Shoes();
                            war7.setVisible(false);
                            shoes.setFirma(firma.getText().getText());
                            shoes.setColor(color.getText().getText());
                            shoes.setModel(model.getText().getText());
                            shoes.setPrice(Double.parseDouble(prise.getText().getText()));
                            shoes.setSize(Integer.parseInt (size.getText().getText()));
                            shoes.setCount(Integer.parseInt (count.getText().getText()));
                            shoesFacade.create(shoes);
                            
                            Sale sale =new Sale();
                            sale.setPerson(personFacade.findById(id));
                            sale.setShoes(shoes);
                            saleFacade.create(sale);
                            firma.getText().setText("");
                            color.getText().setText("");
                            model.getText().setText("");
                            prise.getText().setText("");
                            size.getText().setText("");
                            count.getText().setText("");
                            
                            list_purchases = new list_sale(w-50,"aaa",w,300);
                            shopping.removeAll();
                            shopping.add(my_purchases);

                            purchases.removeAll();
                            purchases.add(my_money);
                            purchases.add(list_purchases);
                        }
                        catch(Exception aq){
                            war7.setVisible(true);
                        }
                    }
                }
            });
            sells.add(button_sell);
            
            
            tabs.add("Buy products",purchases);
            purchases.add(my_money);
            purchases.add(list_purchases);
            button button_buy = new button("Buy product", 150, 30);
            purchases.add(button_buy);
            button_buy.getButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                     Sale value = list_purchases.getList().getSelectedValue();
                    if(personFacade.findById(id).getPurse()>= value.getShoes().getPrice() && value.getShoes().getCount()>0){
                        personFacade.findById(id).setPurse(Math.ceil(
                        personFacade.findById(id).getPurse()*100-value.getShoes().getPrice()*100)/100);
                        value.getShoes().setCount(value.getShoes().getCount()-1);
                        value.getPerson().setIncome(Math.ceil(
                        value.getPerson().getIncome()*100+value.getShoes().getPrice()*100)/100);
                        value.getPerson().setPurse(Math.ceil(
                        value.getPerson().getPurse()*100+value.getShoes().getPrice()*100)/100);
                        personFacade.edit(personFacade.findById(id));
                        personFacade.edit(value.getPerson());
                        shoesFacade.edit(value.getShoes());
                        panel.revalidate();
                        panel.repaint();
                        
                        Purchase purchase = new Purchase();
                        purchase.setPerson(personFacade.findById(id));
                        purchase.setShoes(value.getShoes());
                        purchaseFacade.create(purchase);
                        
                        my_purchases = new list_purchase(w-50,"aaa",w,300);
                        list_purchases = new list_sale(w+200,"aaa",w,300);
                        
                        shopping.removeAll();
                        shopping.add(my_purchases);
                        
                        purchases.removeAll();
                        purchases.add(my_money);
                        purchases.add(list_purchases);
                }
            }});
            
    }
    
}
