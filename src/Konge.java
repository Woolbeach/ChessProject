public class Konge extends Brikker {

    public Konge(int x,int y,boolean is_white)
    {
        super(x,y,is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, Brikker[][] board) {
        Brikker possiblePiece = board[destination_x][destination_y];
        System.out.println(possiblePiece);
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
        // kongen må kun rykke i en cirkel så har sagt at ændringen på begge variabler må højst være 1
        if(Math.abs(this.getX()-destination_x)>1 || Math.abs(this.getY()-destination_y)>1)
        {
            return false;
        }
        this.setX(destination_x);
        this.setY(destination_y);
        return true;
    }
}
