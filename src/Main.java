import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BoardLogic newGame = new BoardLogic();


        final int[] clicks = {0};
        final int[] oldxpos = new int[1];
        final int[] oldypos = new int[1];
        int square = 80;

        GrafikPanel panel = new GrafikPanel(square, newGame.boardTracking);                          // opret panelet
        JFrame vindue = new JFrame("Skak");                                    // opret et vindue på skærmen

        JPanel mainPanel = new JPanel();




        Dimension mySize = new Dimension();
        mySize.setSize(square * 8, square * 8);
        panel.setPreferredSize(mySize);


        LayoutManager mitLay = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = mainPanel.getComponentCount()+2;
        gbc.gridy = 0;

        mainPanel.setLayout(mitLay);
        UI mitUI = new UI();
        mainPanel.add(mitUI.$$$getRootComponent$$$(),gbc);

        gbc.gridx = 0;

        mainPanel.add(panel,gbc);


        vindue.add(mainPanel);                                                      // vis panelet i vinduet

        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                   // reagér på luk
        vindue.setSize(square * 8 + 200, square * 9);                       // sæt vinduets størrelse
        vindue.setResizable(false);
        vindue.pack();
        vindue.setVisible(true);                                                    // åbn vinduet

        vindue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clicks[0]++;
                int xpos = e.getX() - 12;
                int ypos = e.getY() - 31;

                System.out.println("clicked at square " + xpos / square + " , " + ypos / square);
                System.out.println("piece id:" + newGame.boardTracking[ypos / square][xpos / square]);
                if (clicks[0] > 1) {
                    newGame.movepiece(newGame.whitesTurn, oldxpos[0], oldypos[0], xpos / square, ypos / square, newGame.boardTracking, newGame.pieceLogic);
                    clicks[0] = 0;
                    vindue.repaint();
                } else {
                    oldxpos[0] = xpos / square;
                    oldypos[0] = ypos / square;
                    int squarex = xpos / square;
                    int squarey = ypos / square;
                    Color selectedColor = new Color(18, 50, 150, 127);
                    Color movesColor = new Color(188, 10, 40, 100);
                    //draw selected square
                    Graphics g = mainPanel.getGraphics();
                    g.setColor(Color.blue);
                    g.drawRect(squarex * square, squarey * square, square, square);
                    g.setColor(selectedColor);
                    g.fillRect(squarex * square, squarey * square, square, square);

                    //moves
                    g.setColor(movesColor);
                    // Filip har flyttet switch casen ned i movepiece
                }
            }
        });
    }
}

