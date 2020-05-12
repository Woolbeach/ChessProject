public class Queen extends GamePiece {

    public Queen(int x, int y, boolean is_white)
    {
        super(x,y,is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];
        // gør at den ikke kan bevæge sig hen over en af dens egen farve
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
        // Bevægelsesrummet for dronning. Det er tårnet + løber
        if (this.getX() !=destination_x && this.getY() != destination_y && (Math.abs(this.getX()-destination_x)==Math.abs(this.getY()-destination_y))!=true) {

            return false;
        }
        // dette er til så den ikke kan springe over en anden blok
        String direction="";
        if(destination_x> this.getX())
        {
            direction="south";
        }
        if(destination_x< this.getX())
        {
            direction="north";
        }
        if(destination_y> this.getY())
        {
            direction="east";
        }
        if(destination_y< this.getY())
        {
            direction="west";
        }
        if(destination_x> this.getX()&& destination_y<this.getY())
        {
            direction="southwest";
        }

        if(destination_x> this.getX()&&destination_y>this.getY())
        {
            direction="southeast";
        }

        if(destination_x< this.getX()&&destination_y< this.getY())
        {
            direction="northwest";
        }

        if(destination_x< this.getX()&&destination_y> this.getY())
        {
            direction="northeast";
        }
        if(direction.equals("south"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()+i][this.getY()];
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("north"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()-i][this.getY()];
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("east"))
        {
            int spaces_to_move=Math.abs(destination_y - this.getY());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()][this.getY()+i];;
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("west"))
        {
            int spaces_to_move=Math.abs(destination_y - this.getY());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()][this.getY()-i];
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("southwest"))
        {
            int spaces_to_move=Math.abs(destination_y - this.getY());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()+i][this.getY()-i];
                if(p != null)
                {
                    return false;
                }
            }
        }
        //denne går op til venstre
        if(direction.equals("northwest"))
        {
            int spaces_to_move=Math.abs(destination_y - this.getY());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()-i][this.getY()-i];
                if(p != null)
                {
                    return false;
                }
            }
        }
        // denne går op og til højre
        if(direction.equals("northeast"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()-i][this.getY()+i];
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("southeast"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()+i][this.getY()+i];
                if(p != null)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
