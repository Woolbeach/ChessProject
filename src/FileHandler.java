import java.io.*;

public class FileHandler {
    BoardLogic logicClass;

    FileHandler(BoardLogic input) {
        logicClass = input;
    }

    //denne metode opretter en fil og gemmer 4 koordinater fra 4 undo-arrays, som reflekterer start koordinatsæt og slut koordinatsæt for hver tur
    public void saveGame(int x, int numberOfTurns) {
        try {

            //gemmer under navnet save + den valgte save slot
            FileWriter file = new FileWriter("save" + x);
            PrintWriter out = new PrintWriter(file);

            for (int i = 0; i < numberOfTurns; i++) {
                out.println(logicClass.undoFromX[i] + "," + logicClass.undoFromY[i] + "," + logicClass.undoToX[i] + "," + logicClass.undoToY[i] + ",");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //denne metode rydder de 4 undo-arrays og indlæser derefter filen save + den valgte save slot
    public void loadGame(int x) {
        int i = 0;
        for (int j = 0; j < logicClass.numberOfTurns; j++) {
            logicClass.undoFromX[i] = 0;
            logicClass.undoFromY[i] = 0;
            logicClass.undoToX[i] = 0;
            logicClass.undoToY[i] = 0;
        }
        try {
            FileReader file = new FileReader("save" + x);
            BufferedReader in = new BufferedReader(file);
            String currentLine = in.readLine();

            //læser filen så længe den ikke er nået bunden, splitter koordinaterne ved komma, og indsætter disse data i de 4 respektive undo arrays
            while (currentLine != null) {
                String[] data = currentLine.split(",");
                logicClass.undoFromX[i] = Integer.valueOf(data[0]);
                logicClass.undoFromY[i] = Integer.valueOf(data[1]);
                logicClass.undoToX[i] = Integer.valueOf(data[2]);
                logicClass.undoToY[i] = Integer.valueOf(data[3]);
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
        logicClass.newGame();

        //for hver tur i det indlæste gemte spil, udføres handlingen, indtil den sidste tur er udført. Spillet er nu indlæst.
        for (int j = 0; j <= tempNumberOfTurns; j++) {
            int temp1 = logicClass.boardTracking[logicClass.undoFromY[j]][logicClass.undoFromX[j]];
            GamePiece currentPiece = logicClass.pieceLogic[logicClass.undoFromY[j]][logicClass.undoFromX[j]];

            logicClass.pieceLogic[logicClass.undoFromY[j]][logicClass.undoFromX[j]] = null;
            logicClass.boardTracking[logicClass.undoFromY[j]][logicClass.undoFromX[j]] = 0;
            logicClass.boardTracking[logicClass.undoToY[j]][logicClass.undoToX[j]] = temp1;
            logicClass.pieceLogic[logicClass.undoToY[j]][logicClass.undoToX[j]] = currentPiece;

            currentPiece.update(logicClass.undoToY[j], logicClass.undoToX[j]);
            logicClass.whitesTurn = !logicClass.whitesTurn;
        }

        //spillets tur-tæller opdateres til at være én højere end de gemte data
        logicClass.numberOfTurns = tempNumberOfTurns + 1;

    }

}
