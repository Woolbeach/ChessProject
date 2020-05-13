public class Bishop extends GamePiece {

    public Bishop(int x, int y, boolean is_white) {
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
        //løbere må kun bevæge sig diagonalt på brættet
        if ((Math.abs(this.getX() - destination_x) == Math.abs(this.getY() - destination_y)) != true) {
            return false;
        }
        String direction = "";

        //definerer retninger relativt til løberens første koordinater
        if (destination_x > this.getX() && destination_y < this.getY()) {
            direction = "southwest";
        }

        if (destination_x > this.getX() && destination_y > this.getY()) {
            direction = "southeast";
        }

        if (destination_x < this.getX() && destination_y < this.getY()) {
            direction = "northwest";
        }

        if (destination_x < this.getX() && destination_y > this.getY()) {
            direction = "northeast";
        }

        //herefter kan regler udføres, alt efter retning
        if (direction.equals("southwest")) {
            int spaces_to_move = Math.abs(destination_y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() + i][this.getY() - i];
                if (p != null) {
                    return false;
                }
            }
        }

        if (direction.equals("northwest")) {
            int spaces_to_move = Math.abs(destination_y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() - i][this.getY() - i];
                if (p != null) {
                    return false;
                }
            }
        }

        if (direction.equals("northeast")) {
            int spaces_to_move = Math.abs(destination_x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() - i][this.getY() + i];
                if (p != null) {
                    return false;
                }
            }
        }
        if (direction.equals("southeast")) {
            int spaces_to_move = Math.abs(destination_x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() + i][this.getY() + i];
                if (p != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
