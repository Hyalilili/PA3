import java.util.*;
import java.io.*;

public class GameManager
{
    // Instance variables
    private Board board;    // The actual 2048 board
    private String outputFileName;  // File to save the board to when exiting
    
    // TODO PA3
    // GameManager Constructor
    // Generate new game
    GameManager(int boardSize, String outputBoard, Random random)
    {
       this.board = new Board(boardSize, random);
       outputFileName = outputBoard;
    }

    // TODO PA3
    // GameManager Constructor
    // Load a saved game
    GameManager(String inputBoard, String outputBoard, Random random)
        throws IOException
    {
        Scanner sc = new Scanner (new File (inputBoard));
        this.board = new Board (sc.nextInt(),random);
        outputFileName = outputBoard;
    }

    // Main play loop
    // Takes in input from the user to specify moves to execute
    // valid moves are:
    //      w - Move up
    //      s - Move Down
    //      a - Move Left
    //      d - Move Right
    //      q - Quit and Save Board
    //
    //  If an invalid command is received then print the controls 
    //  to remind the user of the valid moves.
    //
    //  Once the player decides to quit or the game is over,
    //  save the game board to a file based on the outputFileName
    //  string that was set in the constructor and then return
    //
    //  If the game is over print "Game Over!" to the terminal
    public void play() throws IOException
    {
        this.printControls();
        Scanner input = new Scanner(System.in);

        do {
            //TODO print board
            String output = input.next();

            System.out.println("input: " + output);
            if(output.equals("w") && board.canMove(Direction.UP)){
                board.move(Direction.UP);
            }
            else if(output.equals("a") && board.canMove(Direction.LEFT)){
                board.move(Direction.LEFT);
            }
            else if(output.equals("s") && board.canMove(Direction.DOWN)){
                board.move(Direction.DOWN);
            }
            else if(output.equals("d") && board.canMove(Direction.RIGHT)){
                board.move(Direction.RIGHT);
            }
            else if(output.equals("q")) {
                board.saveBoard(outputFileName);
                return;
            }
            else{
                System.out.println("Invalid command. Please try again.");
            }
            if(board.isGameOver()){
                return;
            }

            input = new Scanner(System.in);
        } while (input.hasNext());
 
    }

    // Print the Controls for the Game 
    private void printControls()
    {
        System.out.println("  Controls:");
        System.out.println("    w - Move Up");
        System.out.println("    s - Move Down");
        System.out.println("    a - Move Left");
        System.out.println("    d - Move Right");
        System.out.println("    q - Quit and Save Board");
        System.out.println();
    }
}
