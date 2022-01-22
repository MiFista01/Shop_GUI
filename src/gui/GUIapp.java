package gui;


import classes.Person;
import classes.Purchase;
import classes.Shoes;
import facade.PersonFacade;
import facade.PurchaseFacade;
import facade.ShoesFacade;
import gui.components.button;
import gui.components.editor;
import gui.components.label;
import gui.components.list_person;
import gui.components.list_purchase;
import gui.components.list_shoes;
import gui.profile.profile_label;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

public class GUIapp extends JFrame{
    public static int w = 400;
    public static int h = 200;
    
    PersonFacade personFacade = new PersonFacade(Person.class);
    ShoesFacade shoesFacade = new ShoesFacade(Shoes.class);
    PurchaseFacade purchaseFacade = new PurchaseFacade(Purchase.class);
    
    JPanel panel = new JPanel();
    label program_topic = new label(w,50,"Shop of shoes",1,15);
    label warning = new label(w,20,"You don't have some information",1,10);
    button author;
    button reg;
    
    JPanel my_advert = new JPanel();
    static GUIapp gui =  new GUIapp();
    
    public static Long id;


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
                //label1.setForeground(new java.awt.Color(255,0,0));
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
        w = 700;
        h = 500;
        GUIapp.this.setSize(w,h);
        panel.removeAll();
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(w, h));
        tabs.setMinimumSize(tabs.getPreferredSize());
        tabs.setMaximumSize(tabs.getPreferredSize());
        panel.add(tabs);

        JPanel profile = new JPanel();
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
                    panel.removeAll();
                    panel.add(program_topic);
                    panel.add(reg);
                    panel.add(author);
                }
            });
            profile.add(add_money);
            profile.add(log_out);
            
            
        JPanel purchases = new JPanel();
        tabs.add("My purchase", purchases);
        list_purchase list2 = new list_purchase(w-50,"aaa",w,200);
        purchases.add(list2);

        
        JPanel buy = new JPanel();
        tabs.add("buy shoes", buy);
        label my_money = new label(w,30,"Money: "+personFacade.findById(id).getPurse(),1,15);
        list_shoes list1 = new list_shoes(w-50,"aaa",w,200);
        buy.add(my_money);
        buy.add(list1);
        button BUY = new button("Buy",100,20);
        BUY.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Shoes value = list1.getList().getSelectedValue();
                if(personFacade.findById(id).getPurse()>= value.getPrice() && value.getCount()>0){
                    personFacade.findById(id).setPurse(Math.ceil(
                    personFacade.findById(id).getPurse()*100-value.getPrice()*100)/100);
                    value.setCount(value.getCount()-1);
                    value.getPerson().setIncome(value.getPerson().getIncome()+value.getPrice());
                    value.getPerson().setPurse(Math.ceil(
                    value.getPerson().getPurse()*100+value.getPrice()*100)/100);
                    personFacade.edit(personFacade.findById(id));
                    personFacade.edit(value.getPerson());
                    shoesFacade.edit(value);
                    panel.revalidate();
                    panel.repaint();
                    my_money.getLabel().setText("Money: "+personFacade.findById(id).getPurse());
                    profile_purse.getLabel().setText("Purse: "+personFacade.findById(id).getPurse());
                    Purchase purchas = new Purchase();
                    purchas.setPerson(personFacade.findById(id));
                    purchas.setShoes(value);
                    purchaseFacade.create(purchas);
                    buy.removeAll();
                    list_shoes list1 = new list_shoes(w-50,"aaa",w,200);
                    buy.add(my_money);
                    buy.add(list1);
                    
                }
            }
        });
        buy.add(BUY);
        
        JPanel sell = new JPanel();
        tabs.add("Sell shoes", sell);
        label war1 = new label(w,20,"You didn't write firma's name",1,10);
        label war2 = new label(w,20,"You didn't write shoes color",1,10);
        label war3 = new label(w,20,"You didn't write model",1,10);
        label war4 = new label(w,20,"You didn't write prise",1,10);
        label war5 = new label(w,20,"You didn't write size",1,10);
        label war6 = new label(w,20,"You didn't write count",1,10);
        label war7 = new label(w,30,"Inappropriate data",1,15);
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
        sell.add(firma);
        sell.add(war1);
        sell.add(color);
        sell.add(war2);
        sell.add(model);
        sell.add(war3);
        sell.add(prise);
        sell.add(war4);
        sell.add(size);
        sell.add(war5);
        sell.add(count);
        sell.add(war6);
        sell.add(war7);
        

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
        sell.add(clean);
        button SELL = new button("Sell shoes",100,30);
        SELL.getButton().addActionListener(new ActionListener(){
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
                        war7.setVisible(false);
                        Shoes shoes = new Shoes();
                        shoes.setFirma(firma.getText().getText());
                        shoes.setColor(color.getText().getText());
                        shoes.setModel(model.getText().getText());
                        shoes.setPrice(Double.parseDouble(prise.getText().getText()));
                        shoes.setSize(Integer.parseInt (size.getText().getText()));
                        shoes.setCount(Integer.parseInt (count.getText().getText()));
                        shoes.setPerson(personFacade.findById(id));
                        shoesFacade.create(shoes);
                        
                        firma.getText().setText("");
                        color.getText().setText("");
                        model.getText().setText("");
                        prise.getText().setText("");
                        size.getText().setText("");
                        count.getText().setText("");
                        buy.removeAll();
                        list_shoes list1 = new list_shoes(w-50,"aaa",w,200);
                        buy.add(my_money);
                        buy.add(list1);
                        panel.revalidate();
                        panel.repaint();
                        my_advert.removeAll();
                        list_person list3 = new list_person(w-50,"aaa",w,200);
                        my_advert.add(list3);
                    }
                    catch(Exception aq){
                        war7.setVisible(true);
                    }

                }
                
            }
            
        });
        sell.add(SELL);
        my_advert = new JPanel();
        tabs.add("My advert",my_advert);
        list_person list3 = new list_person(w-50,"aaa",w,200);
        my_advert.add(list3);
        button change = new button("Chsnge product",150,30);
        change.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Shoes value = list3.getList().getSelectedValue();
                if (value != null){
                    JFrame frame = new JFrame();
                    JPanel panel2 = new JPanel();
                    frame.add(panel2);
                    w = 700;
                    h = 500;
                    frame.setSize(w,h);
                    frame.setVisible(true);
                    label war1 = new label(w,20,"You didn't write firma's name",1,10);
                    label war2 = new label(w,20,"You didn't write shoes color",1,10);
                    label war3 = new label(w,20,"You didn't write model",1,10);
                    label war4 = new label(w,20,"You didn't write prise",1,10);
                    label war5 = new label(w,20,"You didn't write size",1,10);
                    label war6 = new label(w,20,"You didn't write count",1,10);
                    label war7 = new label(w,30,"Inappropriate data",1,15);
                    war1.setVisible(false);
                    war2.setVisible(false);
                    war3.setVisible(false);
                    war4.setVisible(false);
                    war5.setVisible(false);
                    war6.setVisible(false);
                    war7.setVisible(false);
                    
                    editor firma = new editor(w,20,"firma:",1,12,w/3,20,w/3*2,20);
                    editor color = new editor(w,20,"color:",1,12,w/3,20,w/3*2,20);
                    editor model = new editor(w,20,"model:",1,12,w/3,20,w/3*2,20);
                    editor prise = new editor(w,20,"prise:",1,12,w/3,20,w/3*2,20);
                    editor size = new editor(w,20,"size:",1,12,w/3,20,w/3*2,20);
                    panel2.add(firma);
                    panel2.add(war1);
                    panel2.add(color);
                    panel2.add(war2);
                    panel2.add(model);
                    panel2.add(war3);
                    panel2.add(prise);
                    panel2.add(war4);
                    panel2.add(size);
                    panel2.add(war5);
                    //gui.setEnabled(false);
                    button changes = new button("change",150,30);
                    changes.getButton().addActionListener(new ActionListener(){
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
                        (!size.getText().getText().equals(""))){
                            try{
                                war7.setVisible(false);
                                Shoes shoes = new Shoes();
                                shoes.setFirma(firma.getText().getText());
                                shoes.setColor(color.getText().getText());
                                shoes.setModel(model.getText().getText());
                                shoes.setPrice(Double.parseDouble(prise.getText().getText()));
                                shoes.setSize(Integer.parseInt (size.getText().getText()));
                                shoes.setPerson(personFacade.findById(id));
                                shoesFacade.create(shoes);

                                firma.getText().setText("");
                                color.getText().setText("");
                                model.getText().setText("");
                                prise.getText().setText("");
                                size.getText().setText("");
                                my_advert.removeAll();
                                list_shoes list3 = new list_shoes(w-50,"aaa",w,200);
                                my_advert.add(list3);
                                my_advert.revalidate();
                                my_advert.repaint();

                            }
                            catch(Exception aq){
                                war7.setVisible(true);
                            }

                        }
                    }
                });
                panel2.add(changes);
                }
                
            }
        });
        my_advert.add(change);
    }
    
}
