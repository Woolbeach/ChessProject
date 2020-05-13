import java.io.*;

public class FileHandler {
    BoardLogic boardLogic;

    FileHandler(BoardLogic input) {
        boardLogic = input;
    }

    //denne metode opretter en fil og gemmer 4 koordinater fra 4 undo-arrays, som reflekterer start koordinatsæt og slut koordinatsæt for hver tur
    public void saveGame(int x, int numberOfTurns) {
        try {

            //gemmer under navnet save + den valgte save slot
            FileWriter file = new FileWriter("save" + x);
            PrintWriter out = new PrintWriter(file);

            for (int i = 0; i < numberOfTurns; i++) {
                out.println(boardLogic.undoFromX[i] + "," + boardLogic.undoFromY[i] + "," + boardLogic.undoToX[i] + "," + boardLogic.undoToY[i] + ",");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //denne metode rydder de 4 undo-arrays og indlæser derefter filen save + den valgte save slot
    public void loadGame(int x) {
        int i = 0;
        for (int j = 0; j < boardLogic.numberOfTurns; j++) {
            boardLogic.undoFromX[i] = 0;
            boardLogic.undoFromY[i] = 0;
            boardLogic.undoToX[i] = 0;
            boardLogic.undoToY[i] = 0;
        }
        try {
            FileReader file = new FileReader("save" + x);
            BufferedReader in = new BufferedReader(file);
            String currentLine = in.readLine();

            //læser filen så længe den ikke er nået bunden, splitter koordinaterne ved komma, og indsætter disse data i de 4 respektive undo arrays
            while (currentLine != null) {
                String[] data = currentLine.split(",");
                boardLogic.undoFromX[i] = Integer.valueOf(data[0]);
                boardLogic.undoFromY[i] = Integer.valueOf(data[1]);
                boardLogic.undoToX[i] = Integer.valueOf(data[2]);
                boardLogic.undoToY[i] = Integer.valueOf(data[3]);
                i++;
                currentLine = in.readLine();
            }

            //lukker filen efter brug
            in.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //antallet af ture afgøres ud fra mængden af linjeskift i filen
        int tempNumberOfTurns = i - 1;

        //opretter et nyt spil
        boardLogic.newGame();

        //for hver tur i det indlæste gemte spil, udføres handlingen, indtil den sidste tur er udført. Spillet er nu indlæst.
        for (int j = 0; j <= tempNumberOfTurns; j++) {
            int temp1 = boardLogic.boardTracking[boardLogic.undoFromY[j]][boardLogic.undoFromX[j]];
            GamePiece currentPiece = boardLogic.pieceLogic[boardLogic.undoFromY[j]][boardLogic.undoFromX[j]];

            boardLogic.pieceLogic[boardLogic.undoFromY[j]][boardLogic.undoFromX[j]] = null;
            boardLogic.boardTracking[boardLogic.undoFromY[j]][boardLogic.undoFromX[j]] = 0;
            boardLogic.boardTracking[boardLogic.undoToY[j]][boardLogic.undoToX[j]] = temp1;
            boardLogic.pieceLogic[boardLogic.undoToY[j]][boardLogic.undoToX[j]] = currentPiece;

            currentPiece.update(boardLogic.undoToY[j], boardLogic.undoToX[j]);
            boardLogic.whitesTurn = !boardLogic.whitesTurn;
        }

        //spillets tur-tæller opdateres til at være én højere end de gemte data
        boardLogic.numberOfTurns = tempNumberOfTurns + 1;

    }

}
