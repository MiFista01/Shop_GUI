package gui.renderers;

import classes.Sale;
import gui.GUIapp;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

public class list_my_sales_render extends DefaultListCellRenderer{
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(component instanceof JLabel){
            Sale sale = (Sale) value;
            JLabel label = new JLabel();
            System.out.println();
            label = (JLabel) component;
            
            if (sale.getPerson().getId() == GUIapp.id){
                label.setText(String.format(
                    "Name: "+sale.getPerson().getName()+
                    "  Phone: "+sale.getPerson().getPhone()+
                    "  Firma: "+sale.getShoes().getFirma()+
                    "  Color: "+sale.getShoes().getColor()+
                    "  Model: "+sale.getShoes().getModel()+
                    "  Price: "+sale.getShoes().getPrice()+
                    "  Size: "+sale.getShoes().getSize()+
                    "  Count: "+sale.getShoes().getCount()
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
