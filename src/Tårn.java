
public class Tårn extends Brikker {

    public Tårn()
    {
        super();
    }


    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID, int toID,int[][] board)
    {
        // Grundregel 1. En brik må ikke ramme sin egen farve
        System.out.println(fromID+"   "+toID);
        if((fromID>0 && fromID<14) && (toID>0 && toID<14)){
            return false;
        }
        if((fromID>13 && fromID<27) && (toID>13 && toID<27)){
            return false;
        }
        // tårnet må kun gå op,ned,venstre og højre
        if(fromx!=tox && fromy!=toy){
            return false;
        }
        // finder ud af hvilken vej tårnet gerne vil gå hen

        String direction="";
        if(toy>fromy)
        {
            direction="south";
        }
        if(toy< fromy)
        {
            direction="north";
        }
        if(tox> fromx)
        {
            direction="east";
        }
        if(tox<fromx)
        {
            direction="west";
        }
        if(direction.equals("south"))
        {
            int spaces_to_move=Math.abs(toy-fromy);
            for(int i=1; i<spaces_to_move;i++)
            {
                if(board[fromy+i][fromx]>0)
                {
                    return false;
                }
            }
        }

        if(direction.equals("north"))
        {
            int spaces_to_move=Math.abs(toy- fromy);
            for(int i=1; i<spaces_to_move;i++)
            {
                if(board[fromy-i][fromx]>0)
                {
                    return false;
                }
            }
        }
        if(direction.equals("east"))
        {
            int spaces_to_move=Math.abs(toy- fromy);
            for(int i=0; i<=spaces_to_move;i++)
            {
                System.out.println(board[toy][tox-i]);
                if(board[toy][tox-i]>0)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
