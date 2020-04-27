public class Pawn extends GamePiece {
    private boolean has_moved;
    public Pawn(int x, int y, boolean is_white) {
        super(x, y, is_white);
        has_moved=false;
    }

    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];
        System.out.println("x:"+destination_x);
        System.out.println("y:"+destination_y);
        // dette er hvad gør at den ikke kan ramme sin egen farve

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
        // dette lange check er for det første hop, siden en bonde må gå 2 frem
        if(has_moved==false && this.isWhite() && this.getX()==6 && (Math.abs(destination_x - this.getX())==2))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[destination_x][destination_y];
                if(p != null)
                {
                    return false;
                }
            }
            has_moved=true;
            this.setX(destination_x);
            this.setY(destination_y);
            return true;
        }
        if(has_moved==false && this.isBlack() && this.getX()==1&&(Math.abs(destination_x - this.getX())==2))
        {
            int spaces_to_move=Math.abs(destination_x - this.getX());
            for(int i=1; i<spaces_to_move;i++)
            {
                GamePiece p=board[destination_x][destination_y];
                if(p != null)
                {
                    return false;
                }
            }
            has_moved=true;
            this.setX(destination_x);
            this.setY(destination_y);
            return true;
        }
        // sørger for at alt andet end 1 gang op, venstre og højre er tilladt og lige omvendt for sort
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
        System.out.println("det er sket3");
        // sørger for at den ikke kan ødelægge en brik foran den og den ikke kan gå til højre op eller venstre op
        // uden der er en brik fra modstanderens side der
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
        this.setX(destination_x);
        this.setY(destination_y);
        return true;


    }
}
