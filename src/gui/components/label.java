package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class label extends JPanel{
    private JLabel label;
    public label(int widhWindow, int height, String text, int bolt, int font) {
        initComponent(widhWindow, height,text, bolt, font);
    }
    private void initComponent(int widhWindow, int height, String text, int bolt, int font){
        this.setPreferredSize(new Dimension(widhWindow, height));
        label = new JLabel(text);
        label.setPreferredSize(new Dimension(widhWindow, height));
        label.setMinimumSize(label.getPreferredSize());
        label.setMaximumSize(label.getPreferredSize());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Tahoma",bolt, font));
        this.add(label);
    }
    public JLabel getLabel(){
        return label;
    }
}
