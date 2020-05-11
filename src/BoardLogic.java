import javax.swing.*;
import java.awt.*;

public class BoardLogic {
    Filehandler filehandler = new Filehandler(this);
    GamePiece[][] pieceLogic = new GamePiece[8][8];
    int[][] boardTracking = new int[8][8];
    Sounds sounds = new Sounds();
    UI ourUI;
    boolean whitesTurn = true;
    int numberOfTurns = 0;
    JFrame fromMain;
    boolean gameOver = false;

    int[] undoFromX = new int[269];
    int[] undoFromY = new int[269];
    int[] undoToX = new int[269];
    int[] undoToY = new int[269];

    public BoardLogic() {
        LoadPieces(boardTracking, pieceLogic);
        sounds.playBackGround();
    }

    public void newGame() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardTracking[i][j] = 0;
                pieceLogic[i][j] = null;
            }
        }
        whitesTurn = true;
        numberOfTurns = 0;
        LoadPieces(boardTracking, pieceLogic);
        fromMain.repaint();
    }

    public void LoadPieces(int[][] boardArray, GamePiece[][] pieceArray) {


        //black pawns
        for (int i = 0; i < 8; i++) {
            pieceArray[1][i] = new Pawn(1, i, false);
            boardArray[1][i] = 1;
        }

        pieceArray[0][0] = new Rook(0, 0, false);
        pieceArray[0][7] = new Rook(0, 7, false);
        boardArray[0][0] = 2;
        boardArray[0][7] = 2;
        //black knights
        pieceArray[0][1] = new Knight(0, 1, false);
        pieceArray[0][6] = new Knight(0, 6, false);
        boardArray[0][1] = 3;
        boardArray[0][6] = 3;
        //black bishops
        pieceArray[0][2] = new Bishop(0, 2, false);
        pieceArray[0][5] = new Bishop(0, 5, false);
        boardArray[0][2] = 4;
        boardArray[0][5] = 4;
        //black king and queen
        pieceArray[0][3] = new Queen(0, 3, false);
        pieceArray[0][4] = new King(0, 4, false);
        boardArray[0][3] = 5;
        boardArray[0][4] = 6;
        //white pawns
        for (int i = 0; i < 8; i++) {
            pieceArray[6][i] = new Pawn(6, i, true);
            boardArray[6][i] = 7;
        }
        //white rooks
        pieceArray[7][0] = new Rook(7, 0, true);
        pieceArray[7][7] = new Rook(7, 7, true);
        boardArray[7][0] = 8;
        boardArray[7][7] = 8;
        //white knights
        pieceArray[7][1] = new Knight(7, 1, true);
        pieceArray[7][6] = new Knight(7, 6, true);
        boardArray[7][1] = 9;
        boardArray[7][6] = 9;
        //white bishops
        pieceArray[7][2] = new Bishop(7, 2, true);
        pieceArray[7][5] = new Bishop(7, 5, true);
        boardArray[7][2] = 10;
        boardArray[7][5] = 10;
        //white king and
        pieceArray[7][3] = new Queen(7, 3, true);
        pieceArray[7][4] = new King(7, 4, true);
        boardArray[7][3] = 11;
        boardArray[7][4] = 12;

    }

    public int movepiece(boolean whosturn, int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {
        if(gameOver){
            System.out.println("can't move, game is over");
            return 0;
        }
        GamePiece currentPiece = pieceArray[fromy][fromx];
        if (currentPiece == null) {
            return 0;
        }
        if (whosturn == currentPiece.isBlack()) {
            return 0;
        } else if (!whosturn == currentPiece.isWhite()) {
            return 0;
        }
        System.out.println(currentPiece);
        if (currentPiece.canMove(toy, tox, pieceArray) == true) {
            undoFromX[numberOfTurns] = fromx;
            undoFromY[numberOfTurns] = fromy;
            undoToX[numberOfTurns] = tox;
            undoToY[numberOfTurns] = toy;
            int temp1 = movepieceonBoard(fromx, fromy, tox, toy, boardArray, pieceArray);
            currentPiece.update(toy, tox);
            whitesTurn = !whitesTurn;
            numberOfTurns++;
            return temp1;
        }
        return 0;
    }

    public int movepieceonBoard(int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {
        int temp1 = boardArray[fromy][fromx];
        int temp2 = 0;
        GamePiece currentPiece = pieceArray[fromy][fromx];
        pieceArray[fromy][fromx] = null;
        boardArray[fromy][fromx] = 0;
        if (boardArray[toy][tox] >= 1) {
            sounds.playAttackSound();
        } else {
            sounds.playMoveSound();
        }
        if (boardArray[toy][tox] == 6 || boardArray[toy][tox] == 12) {
            if(whitesTurn){
                System.out.println("Hvid Vinder");
            }
            else{
                System.out.println("Sort vinder");
            }
            gameOver = true;

            sounds.playWin();
            temp2 = 1;
        }
        boardArray[toy][tox] = temp1;
        pieceArray[toy][tox] = currentPiece;
        return temp2;
    }

    public void Undo(int[][] boardArray, GamePiece[][] pieceArray) {
        if (numberOfTurns <= 0) {
            return;
        }
        int tempNumberOfTurns = numberOfTurns;
        newGame();
        for (int i = 0; i < tempNumberOfTurns - 1; i++) {
            int temp1 = boardArray[undoFromY[i]][undoFromX[i]];
            GamePiece currentPiece = pieceArray[undoFromY[i]][undoFromX[i]];
            pieceArray[undoFromY[i]][undoFromX[i]] = null;
            boardArray[undoFromY[i]][undoFromX[i]] = 0;
            boardArray[undoToY[i]][undoToX[i]] = temp1;
            pieceArray[undoToY[i]][undoToX[i]] = currentPiece;
            currentPiece.update(undoToY[i], undoToX[i]);
            whitesTurn = !whitesTurn;
        }
        numberOfTurns = tempNumberOfTurns - 1;
    }

    public GamePiece getPieceLogic(int x, int y) {
        return pieceLogic[x][y];
    }

}
