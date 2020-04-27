import java.io.File;

public class BoardLogic {

    public BoardLogic(Brikker[][] pieceLogic, int[][] boardTracking){

    }

    public static void LoadPieces(int[][] board2,Brikker[][] board) {
        //black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Bonde(1,i,false);
            board2[1][i]=1;
        }
        //filip har lavet flere brikker da vi har brug for en bønder hver da de har et specielt move med en boolean
        //black rooks
        board[0][0] = new Tårn(0,0,false);
        board[0][7] = new Tårn(0,7,false);
        board2[0][0]=2;
        board2[0][7]=2;
        //black knights
        board[0][1] = new Rider(0,1,false);
        board[0][6] = new Rider(0,6,false);
        board2[0][1]=3;
        board2[0][6]=3;
        //black bishops
        board[0][2] = new Løber(0,2,false);
        board[0][5] = new Løber(0,5,false);
        board2[0][2]=4;
        board2[0][5]=4;
        //black king and queen
        board[0][3] = new Dronning(0,3,false);
        board[0][4] = new Konge(0,4,false);;
        board2[0][3]=5;
        board2[0][4]=6;
        //white pawns
        for (int i = 0; i <8; i++) {
            board[6][i] = new Bonde(6,i,true);
            board2[6][i]=7;
        }
        //white rooks
        board[7][0] = new Tårn(7,0,true);
        board[7][7] = new Tårn(7,7,true);
        board2[7][0]=8;
        board2[7][7]=8;
        //white knights
        board[7][1] = new Rider(7,1,true);
        board[7][6] = new Rider(7,6,true);
        board2[7][1]=9;
        board2[7][6]=9;
        //white bishops
        board[7][2] = new Løber(7,2,true);
        board[7][5] = new Løber(7,5,true);
        board2[7][2]=10;
        board2[7][5]=10;
        //white king and
        board[7][3] = new Dronning(7,3,true);
        board[7][4] = new Konge(7,4,true);
        board2[7][3]=11;
        board2[7][4]=12;
        //bonde, tårn, konge, dronning, springer, hest
    }
    public static void movepiece(int fromx, int fromy, int tox, int toy,int[][] mitbræt, Brikker[][] mitbræt2) {
        Brikker brik = mitbræt2[fromy][fromx];
        System.out.println(brik);
        if(brik == null){
            return;
        }
        if (brik.canMove(toy,tox,mitbræt2) == true) {
            movepieceonBoard(fromx,fromy,tox,toy,mitbræt,mitbræt2);
        }
    }
    public static void movepieceonBoard(int fromx,int fromy,int tox,int toy,int[][] board,Brikker[][] board2){
        File attack = new File("ressources/BrickDamage.wav");
        File move = new File("ressources/MoveBrick.wav");
        int temp1 = board[fromy][fromx];
        Brikker brik= board2[fromy][fromx];
        board2[fromy][fromx]=null;
        board[fromy][fromx] = 0;
        if(board[toy][tox] >=1){
            Main.sound(attack);
        }
        else{
            Main.sound(move);
        }
        if(board[toy][tox] == 6 || board[toy][tox] == 12){
            System.exit(1);
        }
        board[toy][tox] = temp1;
        board2[toy][tox]=brik;

    }
}
