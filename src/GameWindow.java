import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameWindow extends JPanel {
    public int squareSize = 10;
    public int[][] board;
    public boolean selected;
    public int selx, sely;
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

    public GameWindow(int input, int[][] braet) {
        board = braet;
        squareSize = input;
        try {
            //indlæs sorte brikker som buffered images
            blkpawn = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_pawn.png"));
            blkrook = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_rook.png"));
            blkknight = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_knight.png"));
            blkbishop = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_bishop.png"));
            blkqueen = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_queen.png"));
            blkking = ImageIO.read(new File("ressources/chessIcons/DarkSide/black_king.png"));

            //indlæs hvide brikker som buffered images
            whtpawn = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_pawn.png"));
            whtrook = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_rook.png"));
            whtknight = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_knight.png"));
            whtbishop = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_bishop.png"));
            whtqueen = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_queen.png"));
            whtking = ImageIO.read(new File("ressources/chessIcons/WhiteSide/white_king.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(java.awt.Graphics g) {

        //herunder referer g til et Graphics-objekt man kan tegne med
        //tegn først baggrunden på panelet
        super.paintComponent(g);

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

        if (selected) {
            g.drawRect(selx, sely, squareSize, squareSize);

        }

        //tegner brikkerne på brættet
        for (int xaxis = 0; xaxis < 8; xaxis++) {
            for (int yaxis = 0; yaxis < 8; yaxis++) {
                switch (board[xaxis][yaxis]) {

                    case 1: {
                        g.drawImage(blkpawn, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 3: {
                        g.drawImage(blkknight, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 4: {
                        g.drawImage(blkbishop, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 2: {
                        g.drawImage(blkrook, yaxis * squareSize, xaxis * squareSize, null);
                        break;
                    }
                    case 5: {
                        g.drawImage(blkqueen, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 6: {
                        g.drawImage(blkking, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 7: {
                        g.drawImage(whtpawn, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 9: {
                        g.drawImage(whtknight, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 10: {
                        g.drawImage(whtbishop, squareSize * yaxis, squareSize * xaxis, null);
                        break;
                    }
                    case 8: {
                        g.drawImage(whtrook, yaxis * squareSize, xaxis * squareSize, null);
                        break;
                    }
                    case 11: {
                        g.drawImage(whtqueen, yaxis * squareSize, xaxis * squareSize, null);
                        break;
                    }
                    case 12: {
                        g.drawImage(whtking, yaxis * squareSize, xaxis * squareSize, null);
                        break;
                    }
                    default: {

                        //System.out.print("Ups, ukendt briknummer: ") ;
                    }
                }
            }
        }
    }
}