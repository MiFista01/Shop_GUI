
package gui.renderers;

import classes.Purchase;
import facade.PurchaseFacade;
import gui.GUIapp;
import java.awt.Color;
import java.awt.Component;
import java.util.Objects;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

public class list_purchase_render extends DefaultListCellRenderer{
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(component instanceof JLabel){
            Purchase purchase = (Purchase) value;
            JLabel label = new JLabel();
            if (GUIapp.id == purchase.getPerson().getId()){
                label = (JLabel) component;
            
                label.setText(String.format(
                    "Firma: "+purchase.getShoes().getFirma()+"  "+
                    "Color: "+purchase.getShoes().getColor()+"  "+
                    "Model: "+purchase.getShoes().getModel()+"  "+
                    "Size: "+purchase.getShoes().getSize()+"  "+
                    "Price: "+purchase.getShoes().getPrice()+"  "+
                    "Count: "+purchase.getShoes().getCount()
            ));
            }else{
                component = new Component() {}; 
            }

            if(!isSelected){
                label.setBackground(index % 2 == 0 ? background : defaultBackground);
            }
        }
        return component;}
    
}
