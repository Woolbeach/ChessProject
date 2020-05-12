import javax.swing.*;
import java.util.ArrayList;
import java.util.*;
public class BoardLogic {
    FileHandler filehandler = new FileHandler(this);
    GamePiece[][] pieceLogic = new GamePiece[8][8];
    int[][] boardTracking = new int[8][8];
    Sounds sounds = new Sounds();
    UI ourUI;
    boolean whitesTurn = true;
    int numberOfTurns = 0;
    int isChecked = 0;
    JFrame fromMain;
    boolean gameOver = false;

    ArrayList<GamePiece> WhitePieces =new ArrayList<>();
    ArrayList<GamePiece> BlackPieces =new ArrayList<>();

    int[] undoFromX = new int[269];
    int[] undoFromY = new int[269];
    int[] undoToX = new int[269];
    int[] undoToY = new int[269];

    public BoardLogic() {
        LoadPieces(boardTracking, pieceLogic);
        sounds.playBackGround();
    }

    public void newGame() {
        gameOver = false;
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
            BlackPieces.add(pieceArray[1][i]);
            boardArray[1][i] = 1;
        }

        pieceArray[0][0] = new Rook(0, 0, false);
        pieceArray[0][7] = new Rook(0, 7, false);
        BlackPieces.add(pieceArray[0][0]);
        BlackPieces.add(pieceArray[0][7]);
        boardArray[0][0] = 2;
        boardArray[0][7] = 2;
        //black knights
        pieceArray[0][1] = new Knight(0, 1, false);
        pieceArray[0][6] = new Knight(0, 6, false);
        BlackPieces.add(pieceArray[0][1]);
        BlackPieces.add(pieceArray[0][6]);
        boardArray[0][1] = 3;
        boardArray[0][6] = 3;
        //black bishops
        pieceArray[0][2] = new Bishop(0, 2, false);
        pieceArray[0][5] = new Bishop(0, 5, false);
        BlackPieces.add(pieceArray[0][2]);
        BlackPieces.add(pieceArray[0][5]);
        boardArray[0][2] = 4;
        boardArray[0][5] = 4;
        //black king and queen
        pieceArray[0][3] = new Queen(0, 3, false);
        pieceArray[0][4] = new King(0, 4, false);
        BlackPieces.add(pieceArray[0][3]);
        BlackPieces.add(pieceArray[0][4]);
        boardArray[0][3] = 5;
        boardArray[0][4] = 6;
        //white pawns
        for (int i = 0; i < 8; i++) {
            pieceArray[6][i] = new Pawn(6, i, true);
            WhitePieces.add(pieceArray[6][i]);
            boardArray[6][i] = 7;
        }
        //white rooks
        pieceArray[7][0] = new Rook(7, 0, true);
        pieceArray[7][7] = new Rook(7, 7, true);
        WhitePieces.add(pieceArray[7][0]);
        WhitePieces.add(pieceArray[7][7]);
        boardArray[7][0] = 8;
        boardArray[7][7] = 8;
        //white knights
        pieceArray[7][1] = new Knight(7, 1, true);
        pieceArray[7][6] = new Knight(7, 6, true);
        WhitePieces.add(pieceArray[7][1]);
        WhitePieces.add(pieceArray[7][6]);
        boardArray[7][1] = 9;
        boardArray[7][6] = 9;
        //white bishops
        pieceArray[7][2] = new Bishop(7, 2, true);
        pieceArray[7][5] = new Bishop(7, 5, true);
        WhitePieces.add(pieceArray[7][2]);
        WhitePieces.add(pieceArray[7][5]);
        boardArray[7][2] = 10;
        boardArray[7][5] = 10;
        //white king and
        pieceArray[7][3] = new Queen(7, 3, true);
        pieceArray[7][4] = new King(7, 4, true);
        WhitePieces.add(pieceArray[7][3]);
        WhitePieces.add(pieceArray[7][4]);
        boardArray[7][3] = 11;
        boardArray[7][4] = 12;

    }

    public void movepiece(boolean whosturn, int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {
        if(gameOver){
            System.out.println("can't move, game is over");
            return;
        }
        GamePiece currentPiece = pieceArray[fromy][fromx];
        if (currentPiece == null) {
            return;
        }
        if (whosturn == currentPiece.isBlack()) {
            return;
        } else if (!whosturn == currentPiece.isWhite()) {
            return;
        }

        GamePiece currentPieceTo = pieceArray[toy][tox];
        pieceArray[fromy][fromx] = null;
        pieceArray[toy][tox] = currentPiece;
        currentPiece.update(toy, tox);

        isChecked = 0;

        if (whosturn == true && checkWhiteKing(tox, toy) == true) {
            System.out.println("Skak");
            isChecked = 3;
        }
        if(whosturn==true && currentPiece.canMove(BlackPieces.get(15).getX(),BlackPieces.get(15).getY(),pieceArray)){
            System.out.println("Black is checked");
            isChecked = 1;
        }
        if (whosturn == false && checkBlackKing(tox, toy) == true) {
            System.out.println("Skak");
            isChecked = 4;
        }
        if(whosturn==false && currentPiece.canMove(WhitePieces.get(15).getX(),WhitePieces.get(15).getY(),pieceArray)){
            System.out.println("White is checked");
            isChecked = 2;
        }
        pieceArray[fromy][fromx] = currentPiece;
        pieceArray[toy][tox] = currentPieceTo;
        currentPiece.update(fromy, fromx);


        if (currentPiece.canMove(toy, tox, pieceArray) == true) {
            undoFromX[numberOfTurns] = fromx;
            undoFromY[numberOfTurns] = fromy;
            undoToX[numberOfTurns] = tox;
            undoToY[numberOfTurns] = toy;
            movePieceOnBoard(fromx, fromy, tox, toy, boardArray, pieceArray);
            currentPiece.update(toy, tox);
            whitesTurn = !whitesTurn;
            numberOfTurns++;
        }
        return;
    }

    public void movePieceOnBoard(int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {
        int temp1 = boardArray[fromy][fromx];
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
        }
        boardArray[toy][tox] = temp1;
        pieceArray[toy][tox] = currentPiece;
    }

    public void undo(int[][] boardArray, GamePiece[][] pieceArray) {
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
    public boolean checkBlackKing(int tox,int toy){
        int i=0;
        for (GamePiece GamePiece:WhitePieces) {
            if(WhitePieces.get(i).canMove(BlackPieces.get(15).getX(),BlackPieces.get(15).getY(),pieceLogic)){
                if(WhitePieces.get(i).getX()==toy &&WhitePieces.get(i).getY()==tox ){
                    continue;
                }
                return true;
            }
            i++;
        }
        return false;
    }
    public boolean checkWhiteKing(int tox,int toy){
        int i=0;
        for (GamePiece GamePiece:BlackPieces) {
            if(BlackPieces.get(i).canMove(WhitePieces.get(15).getX(),WhitePieces.get(15).getY(),pieceLogic)){
                if(BlackPieces.get(i).getX()==toy &&BlackPieces.get(i).getY()==tox ){
                    continue;
                }
                return true;
            }
            i++;
        }
        return false;
    }

}
