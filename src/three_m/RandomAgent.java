package three_m;

import java.util.ArrayList;
import java.util.List;

public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() { // TODO
    	
    	List<Move> possibleMoves = new ArrayList<Move>(this.board.getPossibleMoves());
    	
    	int chosenMoveIndex = (int) Math.random() * ( possibleMoves.size() );
    	Move chosenMove = possibleMoves.get(chosenMoveIndex);
    	
    	System.out.printf( "[%s (Random Agent)] Moving piece %s to %s.", 
    			this.board.getTurn().getType(), chosenMove.fromCell.getCoordinate().toString(), chosenMove.toCell.getCoordinate().toString() );
    	
        return chosenMove;
    }
}
