
public class Rook extends GamePiece {

    public Rook(int x, int y, boolean is_white)
    {
        super(x,y,is_white);
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];
        // gør at den ikke kan bevæge sig hen over en af dens egen farve
        System.out.println(this.getX());
        System.out.println(this.getY());
        System.out.println(destination_x);
        System.out.println(destination_y);
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
        // dette er til så den ikke kan bevæge sig andet end op/ned og venstre/højre
        if (this.getX() !=destination_x && this.getY() != destination_y)
        {
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
        System.out.println(direction);
        if(direction.equals("south"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                System.out.println("det er sket");
                GamePiece p=board[this.getX()+i][this.getY()];
                System.out.println(p);
                if(p != null)
                {
                    return false;
                }
            }
        }
        if(direction.equals("north"))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            System.out.println(spaces_to_move);
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[this.getX()-i][this.getY()];
                System.out.println(p);
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
                System.out.println(p);
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
        this.setX(destination_x);
        this.setY(destination_y);
        return true;
    }
}
