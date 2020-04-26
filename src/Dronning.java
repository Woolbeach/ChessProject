
public class Dronning extends Brikker {

    public Dronning()
    {
        super();
    }

    @Override
    public boolean canMove(int fromx,int fromy,int tox, int toy,int fromID, int toID)
    {
        return true;
    }
}
