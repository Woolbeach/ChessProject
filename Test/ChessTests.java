
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChessTests
{
    BoardLogic boardLogic = new BoardLogic();

    @Test
    public void TestPawnFirstTimeMove() {
        GamePiece currentPiece = boardLogic.pieceLogic[1][0];
        assertTrue(currentPiece.canMove(2,0,boardLogic.pieceLogic));
        assertTrue(currentPiece.canMove(3,0,boardLogic.pieceLogic));
    }
    @Test
    public void TestpawnKillinganotherpawn()
    {
        GamePiece currentPiece = boardLogic.pieceLogic[6][0];
        GamePiece currentPiece2 = boardLogic.pieceLogic[1][0];
        GamePiece currentPiece3 = boardLogic.pieceLogic[1][1];
        currentPiece.update(4,0);
        currentPiece2.update(3,0);
        currentPiece3.update(3,1);
        assertTrue(currentPiece.canMove(3,0,boardLogic.pieceLogic));
        assertFalse(currentPiece.canMove(3,1,boardLogic.pieceLogic));
    }
    @Test
    public void TestPawnJumpingOverAnotherPiece()
    {
        GamePiece currentPiece = boardLogic.pieceLogic[6][0];
        GamePiece currentPiece2 = boardLogic.pieceLogic[1][0];
        currentPiece2.update(5,0);
        boardLogic.pieceLogic[5][0]=currentPiece2;
        assertFalse(currentPiece.canMove(5,0,boardLogic.pieceLogic));
        assertFalse(currentPiece.canMove(4,1,boardLogic.pieceLogic));
    }
    @Test
    public void TestHittingYourOwnPiece()
    {
        GamePiece currentPiece = boardLogic.pieceLogic[7][0];
        GamePiece currentPiece2 = boardLogic.pieceLogic[7][1];
        assertFalse(currentPiece.canMove(7,1,boardLogic.pieceLogic));
    }
}