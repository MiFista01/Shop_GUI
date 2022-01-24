
package gui.components;

import classes.Sale;
import facade.SaleFacade;
import gui.renderers.list_my_sales_render;
import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class list_my_sales extends JPanel{
    private JList<Sale> list;

    public list_my_sales(int widthList, String text, int widthWindow, int heightPanel) {
        initComponents(widthList, text, widthWindow, heightPanel);
    }

    private void initComponents(int widthList, String text, int widthWindow, int heightPanel) {
        this.setPreferredSize(new Dimension(widthList, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(new list_my_sales_render());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthList, heightPanel));
        scrollPane.setMinimumSize(this.getPreferredSize());
        scrollPane.setMaximumSize(this.getPreferredSize());
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<Sale> getListModel() {
        SaleFacade productFacade = new SaleFacade(Sale.class);
        List<Sale> productsArray = productFacade.findAll();
        DefaultListModel<Sale> defaultListModel = new DefaultListModel<>();
        for (Sale productsArray1 : productsArray) {
            defaultListModel.addElement(productsArray1);
        }
        return defaultListModel;
    }
    
    public JList<Sale> getList() {
        return list;
    }
}
