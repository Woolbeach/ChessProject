
public class Tårn extends Brikker {

    public Tårn(int x,int y,boolean is_white)
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
