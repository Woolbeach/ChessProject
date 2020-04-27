import java.io.File;

public class BoardLogic {
    GamePiece[][] pieceLogic = new GamePiece[8][8];
    int[][] boardTracking = new int[8][8];
    Sounds sounds = new Sounds();
    boolean whitesTurn = true;
    int numberOfTurns = 1;

    public BoardLogic(){
       LoadPieces(boardTracking, pieceLogic);
       sounds.getBackgroundSound();
    }

    public static void LoadPieces(int[][] boardArray, GamePiece[][] pieceArray) {
        //black pawns
        for (int i = 0; i < 8; i++) {
            pieceArray[1][i] = new Pawn(1,i,false);
            boardArray[1][i]=1;
        }

        pieceArray[0][0] = new Rook(0,0,false);
        pieceArray[0][7] = new Rook(0,7,false);
        boardArray[0][0]=2;
        boardArray[0][7]=2;
        //black knights
        pieceArray[0][1] = new Knight(0,1,false);
        pieceArray[0][6] = new Knight(0,6,false);
        boardArray[0][1]=3;
        boardArray[0][6]=3;
        //black bishops
        pieceArray[0][2] = new Bishop(0,2,false);
        pieceArray[0][5] = new Bishop(0,5,false);
        boardArray[0][2]=4;
        boardArray[0][5]=4;
        //black king and queen
        pieceArray[0][3] = new Queen(0,3,false);
        pieceArray[0][4] = new King(0,4,false);;
        boardArray[0][3]=5;
        boardArray[0][4]=6;
        //white pawns
        for (int i = 0; i <8; i++) {
            pieceArray[6][i] = new Pawn(6,i,true);
            boardArray[6][i]=7;
        }
        //white rooks
        pieceArray[7][0] = new Rook(7,0,true);
        pieceArray[7][7] = new Rook(7,7,true);
        boardArray[7][0]=8;
        boardArray[7][7]=8;
        //white knights
        pieceArray[7][1] = new Knight(7,1,true);
        pieceArray[7][6] = new Knight(7,6,true);
        boardArray[7][1]=9;
        boardArray[7][6]=9;
        //white bishops
        pieceArray[7][2] = new Bishop(7,2,true);
        pieceArray[7][5] = new Bishop(7,5,true);
        boardArray[7][2]=10;
        boardArray[7][5]=10;
        //white king and
        pieceArray[7][3] = new Queen(7,3,true);
        pieceArray[7][4] = new King(7,4,true);
        boardArray[7][3]=11;
        boardArray[7][4]=12;

    }
    public void movepiece(boolean whosturn, int fromx, int fromy, int tox, int toy,int[][] boardArray, GamePiece[][] pieceArray) {
        GamePiece currentPiece = pieceArray[fromy][fromx];

        if(currentPiece == null) {
            return;
        }
        if(whosturn == currentPiece.isBlack()){
            return;
        }
        else if(!whosturn == currentPiece.isWhite()){
            return;
        }
        System.out.println(currentPiece);
        if (currentPiece.canMove(toy,tox,pieceArray) == true) {
            movepieceonBoard(fromx,fromy,tox,toy,boardArray,pieceArray);
            whitesTurn = !whitesTurn;
            numberOfTurns++;
        }
    }
    public void movepieceonBoard(int fromx, int fromy, int tox, int toy, int[][] boardArray, GamePiece[][] pieceArray){
        int temp1 = boardArray[fromy][fromx];
        GamePiece currentPiece = pieceArray[fromy][fromx];
        pieceArray[fromy][fromx]=null;
        boardArray[fromy][fromx] = 0;
        if(boardArray[toy][tox] >=1){
            sounds.getAttackSound();
        }
        else{
            sounds.getMoveSound();
        }
        if(boardArray[toy][tox] == 6 || boardArray[toy][tox] == 12){
            System.exit(1);
        }
        boardArray[toy][tox] = temp1;
        pieceArray[toy][tox]=currentPiece;

    }
    public GamePiece getPieceLogic(int x, int y){
        return pieceLogic[x][y];
    }

}
