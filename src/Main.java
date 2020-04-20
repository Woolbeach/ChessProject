import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int square = 80;
        GrafikPanel panel = new GrafikPanel(square);                          // opret panelet
        JFrame vindue = new JFrame("Skak");                                    // opret et vindue på skærmen

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();

        Dimension mySize = new Dimension();
        mySize.setSize(square*8,square*8);

        panel.setPreferredSize(mySize);

        mainPanel.add(panel);

        vindue.add(mainPanel);                                                      // vis panelet i vinduet

        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                   // reagér på luk
        vindue.setSize(square*8+200, square*9);                       // sæt vinduets størrelse
        vindue.pack();
        vindue.setVisible(true);                                                    // åbn vinduet


        vindue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int xpos = e.getX();
                int ypos = e.getY();
                System.out.println("clicked! at: " + xpos+ " " + ypos);
            }
        });
    }
}
