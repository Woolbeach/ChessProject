
public class Queen extends GamePiece {

    public Queen(int x, int y, boolean is_white)
    {
        super(x,y,is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        return true;
        //return super.canMove(destination_x, destination_y, board);
    }
}