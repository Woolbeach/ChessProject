
public class Knight extends GamePiece {

    public Knight(int x, int y, boolean is_white) {
        super(x, y, is_white);
    }

    //arver fra gamePiece
    @Override
    public boolean canMove(int destination_x, int destination_y, GamePiece[][] board) {
        GamePiece possiblePiece = board[destination_x][destination_y];

        //regel #1
        //må ikke angribe sin egen farve
        if (possiblePiece != null) {
            if (possiblePiece.isWhite() && this.isWhite()) {
                return false;
            }
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false;
            }
        }

        //regel #2
        //en springer må gerne hoppe over andre brikker, så der skal kun tages højde for relationen mellem x og y koordinaterne
        if (Math.abs(this.getX() - destination_x) == 2 && Math.abs(this.getY() - destination_y) == 1 || Math.abs(this.getX() - destination_x) == 1 && Math.abs(this.getY() - destination_y) == 2) {
            return true;
        }
        return false;
    }
}
