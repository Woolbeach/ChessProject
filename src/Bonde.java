
public class Bonde extends Brikker {

    public Bonde()
    {
        super();
    }


    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID, int toID,boolean harrykket,int[][] board)
    {
        // Grundregel 1. En brik må ikke ramme sin egen farve
        if(fromID>0 && fromID<14 && toID>0 && toID<14){
            return false;
        }
        if(fromID>13 && fromID<27 && toID>13 && toID<27){
            return false;
        }
        // Hvis bonden ikke har rykket endnu så kan den rykke op til 2 skridt
        // for en sort brik
        if(harrykket==false){
            if(fromID>0 && fromID<14 && tox==fromx && toy-fromy==2){
                int spaces_to_move=2;
                for(int i=1; i<spaces_to_move;i++)
                {
                    if(board[fromy+i][fromx]>0)
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        // for en hvid brik
        if(harrykket==false){
            if(fromID>13 && fromID<30 && tox==fromx && Math.abs(fromy-toy)==2){
                System.out.println("Det er sket");
                int spaces_to_move=2;
                for(int i=1; i<spaces_to_move;i++)
                {
                    System.out.println(board[fromy-i][fromx]);
                    if(board[fromy-i][fromx]>0)
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        // Bonden må gerne rykke en ned eller op
        if(board[toy][tox]==0 && fromID>0 && fromID<14 && fromx-tox==0 && toy-fromy==1){
            return true;
        }
        if(board[toy][tox]==0 && fromID>13 && fromID<30 && fromx-tox==0 && fromy-toy==1){
            return true;
        }
        // hvis der står en brik fra modstanderen ved koordinat x+1,y+1 så må bonden gerne gå derhen
        if(fromID>0 && fromID<14 && Math.abs(fromx-tox)==1 && toy-fromy==1 && board[toy][tox]>13){
            return true;
        }
        if(fromID>13 && fromID<30 && Math.abs(fromx-tox)==1 && fromy-toy==1 && board[toy][tox]>0 && board[toy][tox]<30){
            return true;
        }
        // hvis de andre specielle kriterier ikke er opfyldt så må bonden kun rykke en op eller ned
        if(Math.abs(fromy-tox)>1 || Math.abs(fromx-tox)>0){
            return false;
        }

        return true;
    }
}
