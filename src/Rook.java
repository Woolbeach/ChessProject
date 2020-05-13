
public class Rook extends GamePiece {

    public Rook(int x, int y, boolean is_white) {
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
        //tårnet må ikke bevæge sig diagonalt
        if (this.getX() != destination_x && this.getY() != destination_y) {
            return false;
        }

        //regel #3
        //tårnet kan ikke hoppe over brikker

        //definerer retningen fra tårnets start koordinater
        String direction = "";
        if (destination_x > this.getX()) {
            direction = "south";
        }
        if (destination_x < this.getX()) {
            direction = "north";
        }
        if (destination_y > this.getY()) {
            direction = "east";
        }
        if (destination_y < this.getY()) {
            direction = "west";
        }

        //herefter kan regler udføres, alt efter retning
        if (direction.equals("south")) {
            int spaces_to_move = Math.abs(destination_x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() + i][this.getY()];
                if (p != null) {
                    return false;
                }
            }
        }
        if (direction.equals("north")) {
            int spaces_to_move = Math.abs(destination_x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX() - i][this.getY()];
                if (p != null) {
                    return false;
                }
            }
        }
        if (direction.equals("east")) {
            int spaces_to_move = Math.abs(destination_y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX()][this.getY() + i];
                ;
                if (p != null) {
                    return false;
                }
            }
        }
        if (direction.equals("west")) {
            int spaces_to_move = Math.abs(destination_y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                GamePiece p = board[this.getX()][this.getY() - i];
                if (p != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
