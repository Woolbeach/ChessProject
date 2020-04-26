public class Løber extends Brikker {

    public Løber(int x,int y,boolean is_white)
    {
        super(x,y,is_white);
    }

    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID, int toID,int[][] board)
    {
        // Grundregel 1. En brik må ikke ramme sin egen farve
        if(fromID>0 && fromID<7 && toID>0 && toID<7){
            return false;
        }
        if(fromID>7 && fromID<13 && toID>7 && toID<13){
            return false;
        }
        // løber må kun gå hvor forskellen på x og y er den samme
        if((Math.abs(tox-fromx)==Math.abs(toy-fromy))!=true){
            return false;
        }
        // dette er til så den ikke kan springe over en anden blok
        String direction="";
        if(toy> fromy && tox<fromx)
        {
            direction="southwest";
        }

        if(toy> fromy&&tox>fromx)
        {
            direction="southeast";
        }

        if(toy< fromy&&tox< fromx)
        {
            direction="northwest";
        }

        if(toy< fromy&&tox> fromx)
        {
            direction="northeast";
        }

        //denne her er den der går ned til venstre
        if(direction.equals("southwest"))
        {
            int spaces_to_move=Math.abs(toy - fromy);
            for(int i=1; i<spaces_to_move;i++)
            {
                if(board[fromy+i][fromx-i]>0)
                {
                    return false;
                }
            }
        }
        //denne går op til venstre
        if(direction.equals("northwest"))
        {
            int spaces_to_move=Math.abs(toy - fromy);
            for(int i=1; i<spaces_to_move;i++)
            {

                if(board[fromy-i][fromx-i]>0)
                {
                    return false;
                }
            }
        }
        // denne går op og til højre
        if(direction.equals("northeast"))
        {
            int spaces_to_move=Math.abs(tox - fromx);
            for(int i=1; i<spaces_to_move;i++)
            {

                if(board[fromy-i][fromx+i]>0)
                {
                    return false;
                }
            }
        }
        if(direction.equals("southeast"))
        {
            int spaces_to_move=Math.abs(tox - fromx);
            for(int i=1; i<spaces_to_move;i++)
            {
                if(board[fromy+i][fromx+i]>0)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
