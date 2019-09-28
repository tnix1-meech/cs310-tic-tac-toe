package edu.jsu.mcis;


public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board.length; ++col) {
                board[row][col] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        if (isValidSquare(row,col) ==  true) {
            if(isSquareMarked(row,col) == false) {
                board[row][col] = (xTurn? Mark.X: Mark.O);
                xTurn = !xTurn;
                return true;
            }
        }
        return false;
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */

        if(row >= 0 && row < width) {
            if (col >= 0 && col < width) {
                return true;
            }
        }
        return false; 
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */

        if (isValidSquare(row,col) == true) {
            if (board[row][col] != Mark.EMPTY) {
                return true;
            }
        }
        return false;
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */

        if (isValidSquare(row,col) == true) {
            return board[row][col];
        }
        return null;
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if ( isMarkWin(Mark.X) ) {
            return Result.X;
        }

        if (isMarkWin(Mark.O)) {
            return Result.O;
        }

        if (isTie()) {
            return Result.TIE;
        }

        else {
            return Result.NONE;
        }
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */

        int winCounter = 0;

        // finding wins for vertical

        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < width; ++col) {

                if (getMark(row,col) == mark) {
                    ++winCounter;
                }

                if (winCounter == width) {
                    return true;
                }
                
            }

            winCounter = 0;
            
        }

        // finding wins for horizontal

        for (int col = 0; col < width; ++col) {
            for (int row = 0; row < width; ++row) {

                if (getMark(row,col) == mark) {
                    ++winCounter;
                }

                if (winCounter == width) {
                    return true;
                }
            }

            winCounter = 0;
            
        }

        // finding wins for diagonal, left to right

        for (int row = 0; row < width; ++row) {
            
            if (getMark(row,row) == mark) {
                ++winCounter;
            }

            if (winCounter == width){
                return true;
            }
        }


        winCounter = 0;
        

        // finding wins for diagonal right to left

        for (int row = 0; row < width; ++row) {
            
            if (getMark(row,width - 1 - row) == mark) {
                ++winCounter;
            }
            if (winCounter == width) {
                return true;
            }
        }

         winCounter = 0;

        return false;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board.length; ++col) {

                if (getMark(row,col) == Mark.EMPTY) {
                    return false;
                }
            }
        }

        if (isMarkWin(Mark.X) || isMarkWin(Mark.O)) {
            return false;
        }

        else {
            return true;
        }
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        output.append("012\n");

        for (int row = 0; row < width; ++row) {

            output.append(row + " ");

            for (int col = 0; col < width; ++col) {
                output.append(board[row][col]);
        
            }
            output.append("\n");
        }
        return output.toString();
    }
}