import javax.swing.*;
import java.util.ArrayList;

public class BoardLogic {

    //opretter de nødvendige komponenter såsom et filehandler objekt, integer array af brikker og
    //objekt array af brikker, samt lyd objekt og samtlige variabler der benyttes undervejs
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

    //opretter to arraylists til tjek for skak-condition senere
    ArrayList<GamePiece> WhitePieces = new ArrayList<>();
    ArrayList<GamePiece> BlackPieces = new ArrayList<>();

    //opretter arrays til undo metoden, størrelsen 269 er valgt ud fra de længste kendte spil
    int[] undoFromX = new int[269];
    int[] undoFromY = new int[269];
    int[] undoToX = new int[269];
    int[] undoToY = new int[269];

    public BoardLogic() {
        loadPieces(boardTracking, pieceLogic);
        sounds.playBackGround();
    }

    //metode til oprettelse af et nyt spil, alt fjernes fra brættet, gameOver boolean sættes til false
    //hvid sættes til at starte og derefter indlæses samtlige brikker igen vha. loadPieces
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
        loadPieces(boardTracking, pieceLogic);
        fromMain.repaint();
    }

    //denne metode har til ansvar at indlæse samtlige brikker på deres respektive start position
    public void loadPieces(int[][] boardArray, GamePiece[][] pieceArray) {

        WhitePieces.clear();
        BlackPieces.clear();

        //sorte bønder
        for (int i = 0; i < 8; i++) {
            pieceArray[1][i] = new Pawn(1, i, false);
            BlackPieces.add(pieceArray[1][i]);
            boardArray[1][i] = 1;
        }

        //sorte tårne
        pieceArray[0][0] = new Rook(0, 0, false);
        pieceArray[0][7] = new Rook(0, 7, false);
        BlackPieces.add(pieceArray[0][0]);
        BlackPieces.add(pieceArray[0][7]);
        boardArray[0][0] = 2;
        boardArray[0][7] = 2;

        //sorte riddere
        pieceArray[0][1] = new Knight(0, 1, false);
        pieceArray[0][6] = new Knight(0, 6, false);
        BlackPieces.add(pieceArray[0][1]);
        BlackPieces.add(pieceArray[0][6]);
        boardArray[0][1] = 3;
        boardArray[0][6] = 3;

        //sorte løbere
        pieceArray[0][2] = new Bishop(0, 2, false);
        pieceArray[0][5] = new Bishop(0, 5, false);
        BlackPieces.add(pieceArray[0][2]);
        BlackPieces.add(pieceArray[0][5]);
        boardArray[0][2] = 4;
        boardArray[0][5] = 4;

        //sort konge og dronning
        pieceArray[0][3] = new Queen(0, 3, false);
        pieceArray[0][4] = new King(0, 4, false);
        BlackPieces.add(pieceArray[0][3]);
        BlackPieces.add(pieceArray[0][4]);
        boardArray[0][3] = 5;
        boardArray[0][4] = 6;

        //hvide bønder
        for (int i = 0; i < 8; i++) {
            pieceArray[6][i] = new Pawn(6, i, true);
            WhitePieces.add(pieceArray[6][i]);
            boardArray[6][i] = 7;
        }
        //hvide tårne
        pieceArray[7][0] = new Rook(7, 0, true);
        pieceArray[7][7] = new Rook(7, 7, true);
        WhitePieces.add(pieceArray[7][0]);
        WhitePieces.add(pieceArray[7][7]);
        boardArray[7][0] = 8;
        boardArray[7][7] = 8;

        //hvide riddere
        pieceArray[7][1] = new Knight(7, 1, true);
        pieceArray[7][6] = new Knight(7, 6, true);
        WhitePieces.add(pieceArray[7][1]);
        WhitePieces.add(pieceArray[7][6]);
        boardArray[7][1] = 9;
        boardArray[7][6] = 9;

        //hvide løbere
        pieceArray[7][2] = new Bishop(7, 2, true);
        pieceArray[7][5] = new Bishop(7, 5, true);
        WhitePieces.add(pieceArray[7][2]);
        WhitePieces.add(pieceArray[7][5]);
        boardArray[7][2] = 10;
        boardArray[7][5] = 10;

        //hvid konge og dronning
        pieceArray[7][3] = new Queen(7, 3, true);
        pieceArray[7][4] = new King(7, 4, true);
        WhitePieces.add(pieceArray[7][3]);
        WhitePieces.add(pieceArray[7][4]);
        boardArray[7][3] = 11;
        boardArray[7][4] = 12;

    }

    //denne metode opdaterer positionen af en given brik-objekt, antaget at det er et muligt træk
    public void movepiece(boolean whosturn, int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {

        //hvis spillet er slut, kan ingen bevæge sig
        if (gameOver) {
            System.out.println("can't move, game is over");
            return;
        }

        //der laves en midlertidlig brik som bruges til at tjekke forskellige ting
        GamePiece currentPiece = pieceArray[fromy][fromx];

        //hvis der ikke er valgt en mulig brik, sker der intet
        if (currentPiece == null) {
            return;
        }

        //hvis det ikke er din tur, kan du ikke bevæge dig
        if (whosturn == currentPiece.isBlack()) {
            return;
        } else if (!whosturn == currentPiece.isWhite()) {
            return;
        }

        //hvis ovenstående er opfyldt, opdateres koordinaterne i integer-arrayet og objekt-arrayet
        GamePiece currentPieceTo = pieceArray[toy][tox];
        pieceArray[fromy][fromx] = null;
        pieceArray[toy][tox] = currentPiece;
        currentPiece.update(toy, tox);

        //herunder tjekkes der for om man er i "skak"
        isChecked = 0;
        int lengthWhite = (WhitePieces.size()) - 1;
        int lengthBlack = (BlackPieces.size()) - 1;

        if (whosturn == true && checkWhiteKing(tox, toy) == true) {
            System.out.println("Check");
            isChecked = 3;
        }
        if (whosturn == true && currentPiece.canMove(BlackPieces.get(lengthBlack).getX(), BlackPieces.get(lengthBlack).getY(), pieceArray)) {
            System.out.println("Black is checked");
            isChecked = 1;
        }
        if (whosturn == false && checkBlackKing(tox, toy) == true) {
            System.out.println("Check");
            isChecked = 4;
        }
        if (whosturn == false && currentPiece.canMove(WhitePieces.get(lengthWhite).getX(), WhitePieces.get(lengthWhite).getY(), pieceArray)) {
            System.out.println("White is checked");
            isChecked = 2;
        }
        pieceArray[fromy][fromx] = currentPiece;
        pieceArray[toy][tox] = currentPieceTo;
        currentPiece.update(fromy, fromx);

        //hvis canMove er opfyldt på den givne brik (brikken kan bevæge sig), opdateres vores undo koordinater og integer-array koordinater, turen skifter til modsatte spiller
        //og tur-tælleren opdateres med 1
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

    //denne metode har til ansvar at bevæge brikken i integer-arrayet
    public void movePieceOnBoard(int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray) {
        int temp1 = boardArray[fromy][fromx];
        GamePiece currentPiece = pieceArray[fromy][fromx];
        GamePiece currentPieceTo = pieceArray[toy][tox];

        //fjerner modsat farvede brik fra feltet, hvis brikken rykker derhen
        if (currentPieceTo != null) {
            if (currentPiece.isWhite()) {
                BlackPieces.remove(currentPieceTo);
            }
            if (currentPiece.isBlack()) {
                WhitePieces.remove(currentPieceTo);
            }
        }

        pieceArray[fromy][fromx] = null;
        boardArray[fromy][fromx] = 0;

        //hvis destinations koordinaterne har en defineret brik på sig, afspilles angrebs lyden
        if (boardArray[toy][tox] >= 1) {
            sounds.playAttackSound();
        } else {

            //hvis ikke, afspilles bevægelses lyden
            sounds.playMoveSound();
        }

        //hvis destinations koordinaterne har en konge på sig, vinder personen der bevæger sig hen på den
        if (boardArray[toy][tox] == 6 || boardArray[toy][tox] == 12) {
            if (whitesTurn) {
                System.out.println("White wins");
            } else {
                System.out.println("Black wins");
            }

            //win lyden afspilles og gameOver boolean bliver true
            gameOver = true;
            sounds.playWin();
        }
        boardArray[toy][tox] = temp1;
        pieceArray[toy][tox] = currentPiece;
    }

    //denne metode har til formål at muliggøre en fortryd knap
    public void undo(int[][] boardArray, GamePiece[][] pieceArray) {

        //man kan ikke fortryde, hvis intet er gjort
        if (numberOfTurns <= 0) {
            return;
        }

        //holder styr på original antallet af ture ved undo-trykket
        int tempNumberOfTurns = numberOfTurns;

        //opretter nyt spil og indlæser samtlige træk igen, minus 1
        newGame();
        for (int i = 0; i < tempNumberOfTurns - 1; i++) {
            int temp1 = boardArray[undoFromY[i]][undoFromX[i]];
            GamePiece currentPiece = pieceArray[undoFromY[i]][undoFromX[i]];
            GamePiece currentPieceTo = pieceArray[undoToY[i]][undoToX[i]];
            pieceArray[undoFromY[i]][undoFromX[i]] = null;
            boardArray[undoFromY[i]][undoFromX[i]] = 0;
            boardArray[undoToY[i]][undoToX[i]] = temp1;
            pieceArray[undoToY[i]][undoToX[i]] = currentPiece;
            currentPiece.update(undoToY[i], undoToX[i]);
            whitesTurn = !whitesTurn;
            if (currentPieceTo != null) {
                if (currentPiece.isWhite()) {
                    BlackPieces.remove(currentPieceTo);
                }
                if (currentPiece.isBlack()) {
                    WhitePieces.remove(currentPieceTo);
                }
            }
        }

        //antallet af ture skal nu nedsættes til én mindre for at reflektere spillets tilstand
        numberOfTurns = tempNumberOfTurns - 1;
    }

    //denne boolean metode tjekker for om den sorte konge er i "skak"
    public boolean checkBlackKing(int tox, int toy) {
        int length = (BlackPieces.size() - 1);
        for (GamePiece GamePiece : WhitePieces) {
            if (GamePiece.canMove(BlackPieces.get(length).getX(), BlackPieces.get(length).getY(), pieceLogic)) {
                if (GamePiece.getX() == toy && GamePiece.getY() == tox) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    //denne boolean metode tjekker for om den hvide konge er i "skak"
    public boolean checkWhiteKing(int tox, int toy) {
        int length = WhitePieces.size() - 1;
        for (GamePiece GamePiece : BlackPieces) {
            if (GamePiece.canMove(WhitePieces.get(length).getX(), WhitePieces.get(length).getY(), pieceLogic)) {
                if (GamePiece.getX() == toy && GamePiece.getY() == tox) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

}
