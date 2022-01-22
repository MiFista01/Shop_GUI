package gui.renderers;

import classes.Shoes;
import gui.GUIapp;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

public class list_person_render extends DefaultListCellRenderer{
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(component instanceof JLabel){
            Shoes shoes = (Shoes) value;
            JLabel label = new JLabel();
            if (GUIapp.id == shoes.getPerson().getId()&& shoes.getCount()>0){
                label = (JLabel) component;
            
                label.setText(String.format(
                    "Name: "+shoes.getPerson().getName()+"  "+
                    "Phone: "+shoes.getPerson().getPhone()+"  "+
                    "Firma: "+shoes.getFirma()+"  "+
                    "Color: "+shoes.getColor()+"  "+
                    "Model: "+shoes.getModel()+"  "+
                    "Size: "+shoes.getSize()+"  "+
                    "Price: "+shoes.getPrice()+"  "+
                    "Count: "+shoes.getCount()
            ));
            }
            else{
               component = new Component() {}; 
            }
            if(!isSelected){
                label.setBackground(index % 2 == 0 ? background : defaultBackground);
            }
        }
        return component;}
    
}
