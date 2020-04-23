public class Brikker {
    
    public Brikker()
    {

    }

    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID,int toID)
    {
        // Grundregel 1. En brik må ikke ramme sin egen farve
        if(fromID>0 && fromID<14 && toID>0 && toID<14){
            return false;
        }
        if(fromID>13 && fromID<27 && toID>13 && toID<27){
            return false;
        }
        return false;
    }
}
