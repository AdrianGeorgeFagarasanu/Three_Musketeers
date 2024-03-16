
package three_m;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanAgent extends Agent {
	
	private final Scanner scanner = new Scanner(System.in);
	
    public HumanAgent(Board board) {
        super(board);
    }
    
    /**
     * Checks whether scanner input choice is wrong
     * @param String of input and list of RIGHT choices
     * @return boolean of whether input IS wrong
     */
    private boolean wrongChoice(List<String> rightChoices, String input) {
    	
    	boolean result = true;
    	for (String choice : rightChoices) {
    		
    		if (choice.equals(input))
    			result = false;
    	}
    	
    	return result;
    }
    
    
    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { // TODO
    	
    	List<Cell> possibleCells = new ArrayList<Cell>(this.board.getPossibleCells());
    	List<String> possibleCellSymbols = new ArrayList<String>();
    	
    	for ( Cell cell : possibleCells ) {
    		possibleCellSymbols.add(cell.getCoordinate().toString());
    	}
    	
    	String possibleCellStrings = "[" + String.join(", ", possibleCellSymbols) + "]"; 
    	
    	System.out.printf("[%s] Possible pieces are %s. Enter the piece you want to move: ", this.board.getTurn().getType(), possibleCellStrings);
    	String fromCellChoice = scanner.next().toUpperCase();
        while (wrongChoice(possibleCellSymbols, fromCellChoice)) {
            System.out.printf("%s is an invalid destination. \n", fromCellChoice);
            System.out.printf("[%s] Possible pieces are %s. Enter the piece you want to move: ", this.board.getTurn().getType(), possibleCellStrings);
            fromCellChoice = scanner.next().toUpperCase();
        }
        
        Cell fromCell = null;
        
        for ( Cell cell : possibleCells ) {
        	if ( cell.getCoordinate().toString().equals(fromCellChoice) ) {
        		fromCell = cell;
        	}
        }
        
        List<Cell>  possibleDestinations = new ArrayList<Cell>(this.board.getPossibleDestinations(fromCell));
        List<String> possibleDestinationSymbols = new ArrayList<String>();
        
    	for ( Cell cell : possibleDestinations ) {
    		possibleDestinationSymbols.add(cell.getCoordinate().toString());
    	}
    	
    	String possibleDestinationStrings = "[" + String.join(", ", possibleDestinationSymbols) + "]";
        
        System.out.printf("Possible destinations are %s. Enter where you want to move: ", possibleDestinationStrings);
        
        String toCellChoice = scanner.next().toUpperCase();
        while (wrongChoice(possibleDestinationSymbols, toCellChoice)) {
            System.out.printf("%s is an invalid destination. \n", toCellChoice);
            System.out.printf("Possible destinations are %s. Enter where you want to move: ", possibleDestinationStrings);
            toCellChoice = scanner.next().toUpperCase();
            
        }
        
        Cell toCell = null;
        
        for ( Cell cell : possibleDestinations ) {
        	if ( cell.getCoordinate().toString().equals(toCellChoice) ) {
        		toCell = cell;
        	}
        }
        
       return new Move(fromCell, toCell); 
    }
}
