import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GrafikPanel extends JPanel {
    public int squareSize = 10;
    public int[][] board;
    public boolean selected;
    public int selx,sely;

    public GrafikPanel(int input, int[][] braet) {
        board = braet;
        squareSize = input;
    }

    public void paintComponent(Graphics g) {

        // Herunder referer g til et Graphics-objekt man kan tegne med
        super.paintComponent(g);                // tegn først baggrunden på panelet

        boolean farve = true;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (farve) {
                    g.setColor(Color.white);
                    g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
                    farve = false;
                } else {
                    g.setColor(Color.darkGray);
                    g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
                    farve = true;
                }
            }
            farve = !farve;
        }

        if(selected){
            g.drawRect(selx,sely,squareSize,squareSize);
        }

        BufferedImage blkpawn = null;
        BufferedImage blkrook = null;
        BufferedImage blkknight = null;
        BufferedImage blkbishop = null;
        BufferedImage blkqueen = null;
        BufferedImage blkking = null;

        BufferedImage whtpawn = null;
        BufferedImage whtrook = null;
        BufferedImage whtknight = null;
        BufferedImage whtbishop = null;
        BufferedImage whtqueen = null;
        BufferedImage whtking = null;
        
        try {
            //load black pieces into buffered images

            blkpawn = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_pawn.png"));
            blkrook = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_rook.png"));
            blkknight = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_knight.png"));
            blkbishop = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_bishop.png"));
            blkqueen = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_queen.png"));
            blkking = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_king.png"));

            //load white pieces into buffered images
            whtpawn = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_pawn.png"));
            whtrook = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_rook.png"));
            whtknight = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_knight.png"));
            whtbishop = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_bishop.png"));
            whtqueen = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_queen.png"));
            whtking = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_king.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
//testdada
        for (int xaxis = 0; xaxis < 8; xaxis++) {
            for (int yaxis = 0; yaxis < 8; yaxis++) {
                switch (board[xaxis][yaxis]) {
                    //filip: har lavet flere cases da der er flere brikker
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        {
                        g.drawImage(blkpawn, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 10:{
                        g.drawImage(blkknight, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 11:{
                        g.drawImage(blkbishop, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 9: {
                        g.drawImage(blkrook, yaxis*squareSize, xaxis*squareSize, null);
                        break;
                    }
                    case 12:{
                        g.drawImage(blkqueen, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 13:{
                        g.drawImage(blkking, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                            {
                        g.drawImage(whtpawn, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 23:{
                        g.drawImage(whtknight, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 24:{
                        g.drawImage(whtbishop, squareSize*yaxis, squareSize*xaxis, null);
                        break;
                    }
                    case 22:{
                        g.drawImage(whtrook, yaxis*squareSize, xaxis*squareSize, null);
                        break;
                    }
                    case 25: {
                        g.drawImage(whtqueen, yaxis*squareSize, xaxis*squareSize, null);
                        break;
                    }
                    case 26: {
                        g.drawImage(whtking, yaxis*squareSize, xaxis*squareSize, null);
                        break;
                    }
                    default: {
                        System.out.print("");
                    }
                }
            }
        }
    }


    public static void selector(int xpos, int ypos){

    }
}