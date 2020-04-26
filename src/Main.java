import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        //Filip: har lavet et af hvert brik objekt
        Bonde bonde = new Bonde();
        Dronning dronning = new Dronning();
        Konge konge = new Konge();
        Løber løber = new Løber();
        Rider rider = new Rider();
        Tårn tårn = new Tårn();
        //Filip har lavet en bonderykket array som indeholder om bonden har rykket for første gang
        boolean[] bonderykket = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};

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
                    movepiece(oldxpos[0], oldypos[0], xpos / square, ypos / square, mitbræt,bonde,tårn,rider,løber,dronning,konge,bonderykket);
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

    public static void movepiece(int fromx, int fromy, int tox, int toy, int[][] board,Bonde bonde,Tårn tårn,Rider rider,Løber løber,Dronning dronning,Konge konge,boolean[] bonderykket) {
        int casenummer=board[fromy][fromx];
        System.out.println(casenummer);
        switch (casenummer) {
            case 1:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[0],board)) {
                    System.out.println(bonderykket[0]);
                    movepieceonBoard(fromx, fromy, tox, toy, board);

                    bonderykket[0]=true;
                    return;
                }
                break;
            }
            case 2:{
                System.out.println(bonderykket[1]);
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[1],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[1]=true;
                    return;
                }
                break;
            }
            case 3:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[2],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[2]=true;
                    return;
                }
                break;
            }
            case 4:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[3],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[3]=true;
                    return;
                }

                break;
            }
            case 5:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[4],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[4]=true;
                    return;
                }
                break;
            }
            case 6:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[5],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[5]=true;
                    return;
                }
                break;
            }
            case 7:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[6],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[6]=true;
                    return;
                }
                break;
            }
            case 8:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[7],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);

                    bonderykket[7]=true;
                    return;
                }
                break;
            }
            case 9:{
                if(tårn.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],board)) {
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 10:{
                if(rider.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])) {
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 11:{
                if(løber.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 12:{
                if(dronning.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 13: {
                if(konge.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                }
                break;
            }
            case 14:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[8],board)) {
                    movepieceonBoard(fromx, fromy, tox, toy, board);
                    bonderykket[8]=true;
                    return;
                }
                break;
            }
            case 15:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[9],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[9]=true;
                    return;
                }
                break;
            }
            case 16:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[10],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[10]=true;
                    return;
                }
                break;
            }
            case 17:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[11],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[11]=true;
                    return;
                }

                break;
            }
            case 18:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[12],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[12]=true;
                    return;
                }
                break;
            }
            case 19:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[13],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[13]=true;
                    return;
                }
                break;
            }
            case 20:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[14],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[14]=true;
                    return;
                }
                break;
            }
            case 21:{
                if(bonde.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],bonderykket[15],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    bonderykket[15]=true;
                    return;
                }
                break;
            }
            case 22:{
                if(tårn.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],board)) {
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 23:{
                if(rider.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])) {
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 24:{
                if(løber.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox],board)){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 25:{
                if(dronning.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }
            case 26: {
                if(konge.canMove(fromx,fromy,tox,toy,board[fromy][fromx],board[toy][tox])){
                    movepieceonBoard(fromx,fromy,tox,toy,board);
                    return;
                }
                break;
            }

        }
        if (board[fromy][fromx] == 0) {
            return;
        }
    }
    public static void movepieceonBoard(int fromx,int fromy,int tox,int toy,int[][] board){
        File attack = new File("ressources/BrickDamage.wav");
        File move = new File("ressources/MoveBrick.wav");
        int temp1 = board[fromy][fromx];
        board[fromy][fromx] = 0;
        if(board[toy][tox] >=1){
            sound(attack);
        }
        else{
            sound(move);
        }
        if(board[toy][tox] == 12 || board[toy][tox] == 25){
            System.exit(1);
        }
        board[toy][tox] = temp1;

    }
    public static void LoadPieces(int[][] board) {
        //black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = i+1;
        }
        //filip har lavet flere brikker da vi har brug for en bønder hver da de har et specielt move med en boolean
        //black rooks
        board[0][0] = 9;
        board[0][7] = 9;
        //black knights
        board[0][1] = 10;
        board[0][6] = 10;
        //black bishops
        board[0][2] = 11;
        board[0][5] = 11;
        //black king and queen
        board[0][3] = 13;
        board[0][4] = 12;

        //white pawns
        for (int i = 0; i <8; i++) {
            board[6][i] = i+13+1;
        }
        //white rooks
        board[7][0] = 22;
        board[7][7] = 22;
        //white knights
        board[7][1] = 23;
        board[7][6] = 23;
        //white bishops
        board[7][2] = 24;
        board[7][5] = 24;
        //white king and
        board[7][3] = 25;
        board[7][4] = 26;
        //bonde, tårn, konge, dronning, springer, hest
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

