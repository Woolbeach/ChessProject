public class Pawn extends GamePiece {
    int initialMove = 2;
    public Pawn(int x, int y, boolean is_white) {
        super(x, y, is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];
        //System.out.println("x: "+destination_x);
        //System.out.println("y: "+destination_y);

        //Rule #1
        //Cannot attack own pieces
        if(possiblePiece !=null)
        {
            if(possiblePiece.isWhite() && this.isWhite())
            {
                return false;
            }
            if(possiblePiece.isBlack() && this.isBlack())
            {
                return false;
            }
        }

        //Rule #2
        //Pawns may move two fields from their starting position

        //White
        if(this.isWhite() && this.getX() == 6 && (Math.abs(destination_x - this.getX()) == initialMove && destination_y == this.getY()))
        {
            for(int i=1; i < initialMove; i++)
            {
                GamePiece p=board[destination_x][destination_y];
                if(p != null)
                {
                    return false;
                }
            }
            //has_moved=true;
            return true;
        }

        //Black
        if(this.isBlack() && this.getX()==1 && (Math.abs(destination_x - this.getX()) == initialMove && destination_y == this.getY()))
        {
            for(int i=1; i < initialMove;i++)
            {
                GamePiece p=board[destination_x][destination_y];
                if(p != null)
                {
                    return false;
                }
            }
            //has_moved=true;
            return true;
        }

        //Rule #3
        //Pawns may move forwards, and attack opposing force diagonally
        if (this.isWhite())
        {
            if (this.getX()!=destination_x+1 && (this.getY()!=destination_y+1 || this.getY()!=destination_y-1 || this.getY()!=destination_y)) {
                return false;
            }
        }
        if (this.isBlack())
        {
            if (this.getX()!=destination_x-1 && (this.getY()!=destination_y+1 || this.getY()!=destination_y-1 || this.getY()!=destination_y)) {
                return false;
            }
        }
        if (this.getY()-destination_y>1 || this.getY()-destination_y<-1)
        {
            return false;
        }

        if (possiblePiece==null && this.isWhite() && (destination_y+1==this.getY() || destination_y-1==this.getY())){
            return false;
        }
        if(possiblePiece!=null && destination_x+1==this.getX() && possiblePiece.isBlack() && destination_y==this.getY()){
            return false;
        }
        if (possiblePiece==null && this.isBlack() && (destination_y+1==this.getY() || destination_y-1==this.getY())){
            return false;
        }
        if(possiblePiece!=null && destination_x-1==this.getX() && possiblePiece.isWhite()&& destination_y==this.getY()){
            return false;
        }
        return true;
    }
}
