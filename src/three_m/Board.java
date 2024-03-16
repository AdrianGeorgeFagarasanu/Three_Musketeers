
package three_m;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import three_m.Piece.Type;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.loadBoard("Boards/Starter.txt");
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
    	
    	List<Cell> cellList = new ArrayList<Cell>();
    	
    	for (int row = 0; row < this.board.length; row++) {
    		
    		for (int col = 0; col < this.board[0].length; col++) {
    			
    			if ( ( this.board[row][col].hasPiece() ) && ( this.board[row][col].getPiece().getType().equals(Type.MUSKETEER) ) ) {
    				
    				cellList.add(this.board[row][col]);
    				
    			}
    			
    		}
    	}
        return cellList;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	List<Cell> cellList = new ArrayList<Cell>();
    	
    	for (int row = 0; row < this.board.length; row++) {
    		
    		for (int col = 0; col < this.board[0].length; col++) {
    			
    			if ( ( this.board[row][col].hasPiece() ) && this.board[row][col].getPiece().getType().equals(Type.GUARD)) {
    				
    				cellList.add(this.board[row][col]);
    				
    			}
    			
    		}
    	}
        return cellList;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) { // TODO
    	
    	move.toCell.setPiece(move.fromCell.getPiece());
    	
    	move.fromCell.removePiece();
    	
    	if ( turn.equals(Type.MUSKETEER) ) {
    		turn = Type.GUARD;
    	}
    	else {
    		turn = Type.MUSKETEER;
    	}
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) { // TODO
    	
    	this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col] = move.fromCell;
    	
    	this.board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col] = move.toCell;
    	
    	if ( turn.equals(Type.MUSKETEER) ) {
    		turn = Type.GUARD;
    	}
    	else {
    		turn = Type.MUSKETEER;
    	}
    	
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
        List<Cell> validCells = new ArrayList<Cell>(getPossibleDestinations(move.fromCell));
        
        if ( validCells.contains(move.toCell) ) {
        	return true;
        }
        
        return false;
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() { // TODO
    	
    	List<Cell> movableCells = new ArrayList<Cell>();
    	
        for (int row = 0; row < this.board.length; row++) {
        	
        	for (int col = 0; col < this.board[0].length; col++) {
        		
        		if ( ( this.board[row][col].hasPiece() ) && ( this.board[row][col].getPiece().getType().equals(turn) )
        				&& ( getPossibleDestinations(this.board[row][col]).size() >= 1 ) ) {
        			
        			movableCells.add(this.board[row][col]);
        			
        		}
        		
        	}
        }
        
        return movableCells;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	
    	List<Cell> destCellList = new ArrayList<Cell>();
    	
    	
    	//Get all orthogonal cell coordinates surrounding the fromCell coordinate
    	List<Coordinate> allOrthoCoordList = new ArrayList<>( 
    			List.of( new Coordinate(fromCell.getCoordinate().row-1, fromCell.getCoordinate().col),
    					 new Coordinate(fromCell.getCoordinate().row, fromCell.getCoordinate().col-1),
    					 new Coordinate(fromCell.getCoordinate().row+1, fromCell.getCoordinate().col),
    					 new Coordinate(fromCell.getCoordinate().row, fromCell.getCoordinate().col+1) ));
    	
    	//Only add the cells to returned list that are within bounds and type valid per rules
    	for (Coordinate coord : allOrthoCoordList) {
    		 
 
    		if ( (coord.row >= 0 && coord.row < this.board.length) &&
    			 (coord.col >= 0 && coord.col < this.board[0].length) ) {
    		
		    	if (fromCell.getPiece().getType().equals(Type.GUARD)) {
		    		
		    		if ( fromCell.hasPiece() && fromCell.getPiece().canMoveOnto(getCell(coord)) ) {
		    			destCellList.add(getCell(coord));
		    		}
		    			
		        }
		    	else if (fromCell.getPiece().getType().equals(Type.MUSKETEER)) {
		    		
		    		if ( fromCell.hasPiece() && fromCell.getPiece().canMoveOnto(getCell(coord)) ) {
		    			destCellList.add(getCell(coord));
		    		}
		    		
		    	}
    		}
    		
    	}
    	
    	return destCellList;
    	
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	
    	List<Move> moveList = new ArrayList<Move>();
    	
        List<Cell> fromCellList = new ArrayList<Cell>(getPossibleCells());
        
        for (Cell fromCell : fromCellList) {
        	
        	List<Cell> toCellList = new ArrayList<Cell>(getPossibleDestinations(fromCell));
        	
        	for (Cell toCell : toCellList) {
        		moveList.add(new Move(fromCell, toCell));
        	}
        }
        
        return moveList;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
    	
    	List<Cell> musketeerCells = new ArrayList<Cell>(getMusketeerCells());
    	Set<Integer> musketeerRows =  new HashSet<Integer>();
    	Set<Integer> musketeerCols =  new HashSet<Integer>();
    	
    	Boolean musketeerHaveMoves = false;
    	
    	//If the set of all musketeer row values or set of all musketeer col values
    	//have a length of 1, that means they all share the same values! Therefore, all share a row/col
    	for (Cell musketeerCell : musketeerCells) {
    		
    		musketeerRows.add(musketeerCell.getCoordinate().row);
    		musketeerCols.add(musketeerCell.getCoordinate().col);
    		
    		if ( getPossibleDestinations(musketeerCell).size() > 0 ) {
    			musketeerHaveMoves = true;
    		}
    	}
    	
    	if ( (musketeerRows.size() == 1) || (musketeerCols.size() == 1) ) {
    		winner = Type.GUARD;
    		return true;
    	}
    	else if ( !musketeerHaveMoves ) {
    		winner = Type.MUSKETEER;
    		return true;
    	}
    	
    	
    	return false;
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
