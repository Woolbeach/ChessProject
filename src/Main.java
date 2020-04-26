import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Brikker[][] mitbræt2 = new Brikker[8][8];

        int[][] mitbræt = new int[8][8];
        final int[] clicks = {0};
        final int[] oldxpos = new int[1];
        final int[] oldypos = new int[1];
        LoadPieces(mitbræt,mitbræt2);

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

        File music = new File("ressources/background.wav");
        sound(music);

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
                    movepiece(oldxpos[0], oldypos[0], xpos / square, ypos / square, mitbræt,mitbræt2);
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

    public static void movepiece(int fromx, int fromy, int tox, int toy,int[][] mitbrædt, Brikker[][] mitbrædt2) {
        Brikker brik = mitbrædt2[fromy][fromx];
        if (brik.canMove(tox,toy,mitbrædt2) == true) {
            movepieceonBoard(fromx,fromy,tox,toy,mitbrædt,mitbrædt2);
        }
    }
    public static void movepieceonBoard(int fromx,int fromy,int tox,int toy,int[][] board,Brikker[][] board2){
        File attack = new File("ressources/BrickDamage.wav");
        File move = new File("ressources/MoveBrick.wav");
        int temp1 = board[fromy][fromx];
        Brikker brik= board2[fromy][fromx];
        board2[fromy][fromx]=null;
        board[fromy][fromx] = 0;
        if(board[toy][tox] >=1){
            sound(attack);
        }
        else{
            sound(move);
        }
        if(board[toy][tox] == 5 || board[toy][tox] == 11){
            System.exit(1);
        }
        board[toy][tox] = temp1;
        board2[toy][tox]=brik;

    }
    //board2 er den med tal
    //board er den med brikkerne
    public static void LoadPieces(int[][] board2,Brikker[][] board) {
        //black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Bonde(1,i,false);
            board2[1][i]=1;
        }
        //filip har lavet flere brikker da vi har brug for en bønder hver da de har et specielt move med en boolean
        //black rooks
        board[0][0] = new Tårn(0,0,false);
        board[0][7] = new Tårn(0,7,false);
        board2[0][0]=2;
        board2[0][7]=2;
        //black knights
        board[0][1] = new Rider(0,1,false);
        board[0][6] = new Rider(0,6,false);
        board2[0][1]=3;
        board2[0][6]=3;
        //black bishops
        board[0][2] = new Løber(0,2,false);
        board[0][5] = new Løber(0,5,false);
        board2[0][2]=4;
        board2[0][5]=4;
        //black king and queen
        board[0][3] = new Konge(0,3,false);
        board[0][4] = new Dronning(0,4,false);;
        board2[0][3]=5;
        board2[0][4]=6;
        //white pawns
        for (int i = 0; i <8; i++) {
            board[6][i] = new Bonde(6,i,true);
            board2[6][i]=7;
        }
        //white rooks
        board[7][0] = new Tårn(7,0,true);
        board[7][7] = new Tårn(7,7,true);
        board2[7][0]=8;
        board2[7][7]=8;
        //white knights
        board[7][1] = new Rider(7,1,true);
        board[7][6] = new Rider(7,6,true);
        board2[7][1]=9;
        board2[7][6]=9;
        //white bishops
        board[7][2] = new Løber(7,2,true);
        board[7][5] = new Løber(7,5,true);
        board2[7][2]=10;
        board2[7][5]=10;
        //white king and
        board[7][3] = new Konge(7,3,true);
        board[7][4] = new Dronning(7,4,true);
        board2[7][3]=11;
        board2[7][4]=12;
        //bonde, tårn, konge, dronning, springer, hest
    }
    public Brikker getPiece(int x, int y,Brikker[][] board) {
        return board[y][x];
    }
    //This method ensures that the game is able to play sounds, takes a .wav file as parameter.
    public static void sound(File input) {
        try {
            Clip clip = AudioSystem.getClip();
            //Loads in the designated sound input
            clip.open(AudioSystem.getAudioInputStream(input));
            //Starts the clip
            clip.start();
        } catch (Exception e) {
        }
    }

}

