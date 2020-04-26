import java.net.URL;

public class Bonde extends Brikker {

    public Bonde(int x,int y,boolean is_white) {
        super(x, y, is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, Brikker[][] board) {
        return true;
    }
}
