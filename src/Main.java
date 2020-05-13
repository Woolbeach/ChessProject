import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //opretter et boardlogic objekt
        BoardLogic boardLogic = new BoardLogic();

        //opretter integers til navigation af mus og position samt størrelse af brættet
        final int[] clicks = {0};
        final int[] oldxpos = new int[1];
        final int[] oldypos = new int[1];
        int square = 80;

        // opretter panelet og et vindue på skærmen
        GameWindow panel = new GameWindow(square, boardLogic.boardTracking);
        JFrame vindue = new JFrame("Skak");

        boardLogic.fromMain = vindue;

        JPanel mainPanel = new JPanel();

        Dimension mySize = new Dimension();
        mySize.setSize(square * 8, square * 8);
        panel.setPreferredSize(mySize);

        LayoutManager mitLay = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = mainPanel.getComponentCount() + 2;
        gbc.gridy = 0;

        mainPanel.setLayout(mitLay);
        UI mitUI = new UI();
        boardLogic.ourUI = mitUI;
        mitUI.boardLogic = boardLogic;
        mainPanel.add(mitUI.$$$getRootComponent$$$(), gbc);

        gbc.gridx = 0;
        mainPanel.add(panel, gbc);

        //vis panelet i vinduet
        vindue.add(mainPanel);

        //reagerer på luk af vindue og sætter vinduets størrelse
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vindue.setSize(square * 8 + 200, square * 9);
        vindue.setResizable(false);
        vindue.pack();

        //åbn vinduet
        vindue.setVisible(true);

        //spillets mouselistener som reagerer på klik
        vindue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clicks[0]++;
                int xpos = e.getX() - 5;
                int ypos = e.getY() - 31;

                if (clicks[0] > 1) {

                    boardLogic.movepiece(boardLogic.whitesTurn, oldxpos[0], oldypos[0], xpos / square, ypos / square, boardLogic.boardTracking, boardLogic.pieceLogic);

                    clicks[0] = 0;

                    mitUI.update();
                    vindue.repaint();
                    if (boardLogic.gameOver) {
                        mitUI.whoWon(boardLogic.whitesTurn);
                    }

                } else {
                    oldxpos[0] = xpos / square;
                    oldypos[0] = ypos / square;
                    int squarex = xpos / square;
                    int squarey = ypos / square;
                    Color selectedColor = new Color(18, 50, 150, 127);
                    Color movesColor = new Color(188, 10, 40, 100);
                    Color possibleMoves = new Color(0, 150, 0, 100);

                    //tegn den valgte firkant
                    Graphics g = mainPanel.getGraphics();
                    g.setColor(Color.blue);
                    g.drawRect(squarex * square, squarey * square, square, square);
                    g.setColor(selectedColor);
                    g.fillRect(squarex * square, squarey * square, square, square);

                    //bevægelse af brik
                    g.setColor(movesColor);

                    GamePiece possiblePiece = boardLogic.pieceLogic[oldypos[0]][oldxpos[0]];

                    if (possiblePiece != null) {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                {
                                    //farver de mulige felter
                                    if (possiblePiece.canMove(j, i, boardLogic.pieceLogic)) {
                                        g.setColor(possibleMoves);
                                        g.drawRect(i * square, j * square, square, square);
                                        g.fillRect(i * square, j * square, square, square);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}

