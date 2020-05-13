public class King extends GamePiece {

    public King(int x, int y, boolean is_white) {
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
        //kongen må kun bevæge sig én i enhver retning
        if (Math.abs(this.getX() - destination_x) > 1 || Math.abs(this.getY() - destination_y) > 1) {
            return false;
        }
        return true;
    }
}
