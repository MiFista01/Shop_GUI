package gui.components;

import classes.Purchase;
import facade.PurchaseFacade;
import gui.renderers.list_purchase_render;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class list_purchase extends JPanel{
    private JLabel title;
    private JList<Purchase> list;

    public list_purchase(int widthList, String text, int widthWindow, int heightPanel) {
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
        list.setCellRenderer(new list_purchase_render());
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

    private ListModel<Purchase> getListModel() {
        PurchaseFacade productFacade = new PurchaseFacade(Purchase.class);
        List<Purchase> productsArray = productFacade.findAll();
        DefaultListModel<Purchase> defaultListModel = new DefaultListModel<>();
        for (Purchase productsArray1 : productsArray) {
            defaultListModel.addElement(productsArray1);
        }
        return defaultListModel;
    }
    
    public JList<Purchase> getList() {
        return list;
    }
}
