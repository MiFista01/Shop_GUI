/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.profile;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author aleksei
 */
public class profile_label extends JPanel{
    JLabel label = new JLabel();
    public profile_label(int widhWindow, int height, String text, int bolt, int font) {
        initComponent(widhWindow, height,text, bolt, font);
    }
    private void initComponent(int widhWindow, int height, String text, int bolt, int font){
        this.setPreferredSize(new Dimension(widhWindow, height));
        label = new JLabel(text);
        label.setPreferredSize(new Dimension(widhWindow, height));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(new Font("Tahoma",bolt, font));
        this.add(label);
    }
    public JLabel getLabel(){
        return label;
    }
}
