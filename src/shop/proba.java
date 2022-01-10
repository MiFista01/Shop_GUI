/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author aleksei
 */
public class proba extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();

    private final String[] data1 = { "Чай" ,"Кофе"  ,"Минеральная","Морс"};
    private final String[] data2 = { "Ясли","Детсад", "Школа"     , "Институт", 
                                     "Университет"};

    public proba()
    {
        super("Пример со списком JList");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание панели
        JPanel contents = new JPanel();

        // Наполнение модели данными
        for (String string : data2) {
            dlm.add(0, string);
        }
        // Создание кнопки
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dlm.add(dlm.getSize(), "-- Новая запись --");
                validate();
            }
        });
        JList<String> list1 = new JList<String>(data1);
        JList<String> list2 = new JList<String>(dlm);

        // Вектор данных
        Vector<String> big = new Vector<String>();
        for (int i=0; i < 15; i++) {
            big.add("~ " + i + ". ~");
        }
        JList<String> bigList = new JList<String>(big);
        bigList.setPrototypeCellValue("12345");
        
        // Размещение компонентов в панели
        contents.add(add);
        contents.add(new JScrollPane(list1));
        contents.add(new JScrollPane(list2));
        contents.add(new JScrollPane(bigList));

        setContentPane(contents);
        // Вывод окна
        setSize(400, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new proba();
    }
}
