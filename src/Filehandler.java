import java.io.*;


public class Filehandler {
    BoardLogic logicClass;

    Filehandler(BoardLogic input) {
        logicClass = input;
    }

    public void saveGame(int x, int numberOfTurns) {
        try {
            FileWriter file = new FileWriter("save" + x, false);
            PrintWriter out = new PrintWriter(file);

            for (int i = 0; i < numberOfTurns; i++) {
                out.println(logicClass.undoFromX[i] + "," + logicClass.undoFromY[i] + "," + logicClass.undoToX[i] + "," + logicClass.undoToY[i]+",");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadGame(int x) {
        int i = 0;
        try {
            FileReader file = new FileReader("save" + x);
            BufferedReader in = new BufferedReader(file);
            String currentLine = in.readLine();

            while(currentLine != null){
                String[] data = currentLine.split(",");
                logicClass.undoFromX[i] = Integer.valueOf(data[0]);
                logicClass.undoFromY[i] = Integer.valueOf(data[1]);
                logicClass.undoToX[i] = Integer.valueOf(data[2]);
                logicClass.undoToY[i] = Integer.valueOf(data[3]);
                i++;
                currentLine = in.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        int tempNumberOfTurns = i+1;
        logicClass.newGame();
        for(int j=0;j<tempNumberOfTurns-1;j++) {
            int temp1 = logicClass.boardTracking[logicClass.undoFromY[j]][logicClass.undoFromX[j]];
            GamePiece currentPiece = logicClass.pieceLogic[logicClass.undoFromY[j]][logicClass.undoFromX[j]];
            logicClass.pieceLogic[logicClass.undoFromY[j]][logicClass.undoFromX[j]] = null;
            logicClass.boardTracking[logicClass.undoFromY[j]][logicClass.undoFromX[j]] = 0;
            logicClass.boardTracking[logicClass.undoToY[j]][logicClass.undoToX[j]] = temp1;
            logicClass.pieceLogic[logicClass.undoToY[j]][logicClass.undoToX[j]] = currentPiece;
            currentPiece.update(logicClass.undoToY[j],logicClass.undoToX[j]);
            logicClass.whitesTurn =! logicClass.whitesTurn;
        }
        logicClass.numberOfTurns=tempNumberOfTurns-1;


    }


}
