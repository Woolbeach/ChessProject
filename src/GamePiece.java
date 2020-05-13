public class GamePiece {
    private int x;
    private int y;
    final private boolean is_white;
    public GamePiece(int x, int y, boolean is_white) {
        this.is_white = is_white;
        this.x = x;
        this.y = y;
    }
    //en boolean metode til at afgøre om brikken kan bevæge sig til de ønskede koordinater
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        return false;
    }

    //en boolean metode til at afgøre hvilken farve brikken har
    public boolean isWhite()
    {
        return is_white;
    }

    //en boolean metode til at afgøre hvilken farve brikken har
    public boolean isBlack()
    {
        return !is_white;
    }

    //opdaterer x-koordinaten for brikken
    public void setX(int x)
    {
        this.x = x;
    }

    //opdaterer y-koordinaten for brikken
    public void setY(int y)
    {
        this.y = y;
    }

    //tjekker x-koordinaten for brikken
    public int getX() { return x; }

    //tjekker y-koordinaten for brikken
    public int getY()
    {
        return y;
    }

    //opdaterer brikkens koordinatsæt ud fra destinationen af brikken
    public void update(int destination_x,int destination_y){
        this.setX(destination_x);
        this.setY(destination_y);
    }
}


