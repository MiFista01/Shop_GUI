package gui.components;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class button extends JPanel{
    private JButton button;

    public button(String text, int w, int h) {
        initComponents(text, w, h);
    }

    private void initComponents(String text, int w, int h) {
        this.setPreferredSize(new Dimension(w, h));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        button = new JButton(text);
        button.setPreferredSize(new Dimension(w,h));
        button.setMaximumSize(new Dimension(w,h));
        button.setMinimumSize(new Dimension(w,h));
        button.setSize(w-1,h-1);
        this.add(button);
        
    }
    
    public JButton getButton(){
        return button;
    }
}
