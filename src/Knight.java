
public class Knight extends GamePiece {

    public Knight(int x, int y, boolean is_white)
    {
        super(x,y,is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];
        System.out.println(this.getY());
        System.out.println(this.getX());
        if(possiblePiece !=null)
        {
            if(possiblePiece.isWhite()&& this.isWhite())
            {
                return false;
            }
            if(possiblePiece.isBlack()&& this.isBlack())
            {
                return false;
            }
        }
        // siden en springer må gå igennem så skal man bare sørge for at den kun må
        // have en x forskel på 1 eller 3 og en y forksle på 1 eller 3
        if(Math.abs(this.getX() - destination_x) == 2 && Math.abs(this.getY() - destination_y) == 1 || Math.abs(this.getX() - destination_x) == 1 && Math.abs(this.getY() - destination_y) == 2){
            this.setX(destination_x);
            this.setY(destination_y);
            return true;
        }
        return false;
    }
}
