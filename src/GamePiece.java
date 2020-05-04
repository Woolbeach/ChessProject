public class GamePiece {
    private int x;
    private int y;
    final private boolean is_white;
    public GamePiece(int x, int y, boolean is_white) {
        this.is_white = is_white;
        this.x = x;
        this.y = y;
    }

    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        return false;
    }

    public boolean isWhite()
    {
        return is_white;
    }

    public boolean isBlack()
    {
        return !is_white;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX() { return x; }

    public int getY()
    {
        return y;
    }

    public void update(int destination_x,int destination_y){
        this.setX(destination_x);
        this.setY(destination_y);
    }
}


