
public class Rider extends Brikker {

    public Rider()
    {
        super();
    }

    @Override
    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID, int toID)
    {
        // Grundregel 1. En brik må ikke ramme sin egen farve
        System.out.println(fromID+"   "+toID);
        if((fromID>0 && fromID<14) && (toID>0 && toID<14)){
            return false;
        }
        if((fromID>13 && fromID<27) && (toID>13 && toID<27)){
            return false;
        }

        return ((Math.abs(fromx-tox)==2 && Math.abs(fromy-toy)==1) || (Math.abs(fromx-tox)==1 && Math.abs(fromy-toy)==2));
    }
}
