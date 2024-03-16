package three_m.testing;

import org.junit.*;

import three_m.Board;
import three_m.Cell;
import three_m.Coordinate;

public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    }

    @Test
    public void testGetCell() {
        Cell cell = board.getCell(new Coordinate(1, 4));
        Assert.assertNotNull(cell.getPiece());
    }

}
