package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class editor extends JPanel{
    private JLabel title;
    private JTextField editor;
    public editor(int w, int h, String text, int bolt, int font, int we, int he, int we2, int he2) {
        initComponent(w, h,text, bolt, font, we, he, we2, he2);
    }
    private void initComponent(int w, int h, String text, int bolt, int font, int we, int he, int we2, int he2){
        this.setSize(w, h);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(we, he));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(we2-5, he2));
        title = new JLabel(text);
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setSize(new Dimension(we, he));
        title.setFont(new Font("Tahoma",bolt, font));
        panel1.add(title);
        editor = new JTextField();
        editor.setPreferredSize(new Dimension(we2-50, he2));
        panel2.add(editor);        
        this.add(panel1);
        this.add(panel2);
    }
    public JTextField getText(){
        return editor;
    }
}
