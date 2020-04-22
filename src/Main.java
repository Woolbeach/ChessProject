import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int[][] mitbræt = new int[8][8];
        final int[] clicks = {0};
        final int[] oldxpos = new int[1];
        final int[] oldypos = new int[1];
        LoadPieces(mitbræt);


        int square = 80;
        GrafikPanel panel = new GrafikPanel(square, mitbræt);                          // opret panelet
        JFrame vindue = new JFrame("Skak");                                    // opret et vindue på skærmen


        JPanel mainPanel = new JPanel();

        Dimension mySize = new Dimension();
        mySize.setSize(square * 8, square * 8);

        panel.setPreferredSize(mySize);

        mainPanel.add(panel);

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
                System.out.println("piece id:" + mitbræt[ypos/square][xpos/square]);

                if (clicks[0] > 1) {
                    movepiece(oldxpos[0], oldypos[0], xpos / square, ypos / square, mitbræt);
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
                    switch (mitbræt[squarey][squarex]) {
                        case 1: {
                            //black pawn
                            if (mitbræt[squarey + 1][squarex + 1] != 0 && mitbræt[squarey + 1][squarex + 1] > 6) {
                                g.fillRect(squarex * square + square, squarey * square + square, square, square);
                            }
                            if (mitbræt[squarey + 1][squarex - 1] != 0 && mitbræt[squarey + 1][squarex + 1] > 6) {
                                g.fillRect(squarex * square - square, squarey * square + square, square, square);
                            }
                            if (mitbræt[squarey + 1][squarex] == 0) {
                                g.fillRect(squarex * square, squarey * square + square, square, square);
                            }
                            break;
                        }
                        case 4: {
                            //black rook
                            int step = 1;

                            if (mitbræt[squarey + step][squarex] == 0) {
                                g.fillRect(squarex * square, squarey * square + square * step, square, square);
                                step++;

                                if (mitbræt[squarey + step][squarex] == 0) {
                                    g.fillRect(squarex * square, squarey * square + square*step, square, square);
                                    step++;

                                    if (mitbræt[squarey + step][squarex] == 0) {
                                        g.fillRect(squarex * square, squarey * square + square*step, square, square);
                                        step++;
                                    }
                                }
                            }

                            break;
                        }
                    }
                }
            }
        });
    }

    public static void movepiece(int fromx, int fromy, int tox, int toy, int[][] board) {
        if (board[fromy][fromx] == 0) {
            return;
        } else {
            int temp1 = board[fromy][fromx];
            board[fromy][fromx] = 0;
            board[toy][tox] = temp1;
        }
    }

    public static void LoadPieces(int[][] board) {
        //black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = 1;
        }
        //black rooks
        board[0][0] = 4;
        board[0][7] = 4;
        //black knights
        board[0][1] = 2;
        board[0][6] = 2;
        //black bishops
        board[0][2] = 3;
        board[0][5] = 3;
        //black king and queen
        board[0][3] = 6;
        board[0][4] = 5;

        //white pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = 1 + 6;
        }
        //white rooks
        board[7][0] = 4 + 6;
        board[7][7] = 4 + 6;
        //white knights
        board[7][1] = 2 + 6;
        board[7][6] = 2 + 6;
        //white bishops
        board[7][2] = 3 + 6;
        board[7][5] = 3 + 6;
        //white king and
        board[7][3] = 5 + 6;
        board[7][4] = 6 + 6;
        //bonde, tårn, konge, dronning, springer, hest
    }


}

