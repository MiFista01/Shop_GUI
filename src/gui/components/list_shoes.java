package gui.components;

import classes.Shoes;
import facade.ShoesFacade;
import gui.renderers.list_shoes_render;
import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class list_shoes extends JPanel{
    private JLabel title;
    private JList<Shoes> list;

    public list_shoes(int widthList, String text, int widthWindow, int heightPanel) {
        initComponents(widthList, text, widthWindow, heightPanel);
    }

    private void initComponents(int widthList, String text, int widthWindow, int heightPanel) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        label field = new label(8,0,"",1,15);
        this.add(field);
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new list_shoes_render());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList+42, heightPanel));
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<Shoes> getListModel() {
        ShoesFacade productFacade = new ShoesFacade(Shoes.class);
        List<Shoes> productsArray = productFacade.findAll();
        DefaultListModel<Shoes> defaultListModel = new DefaultListModel<>();
        for (Shoes productsArray1 : productsArray) {
            defaultListModel.addElement(productsArray1);
        }
        return defaultListModel;
    }
    
    public JList<Shoes> getList() {
        return list;
    }
}
