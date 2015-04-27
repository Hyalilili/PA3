/**     Sample Board
 *
 *      0   1   2   3aaaa
 *  0   -   -   -   -
 *  1   -   -   -   -
 *  2   -   -   -   -
 *  3   -   -   -   -adadaad
 
 *  The sample board shows the index values for the columns and rows
 *  Remember that you access a 2D array by first specifying the row
 *  and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;

public class Board
{
    public final int NUM_START_TILES = 2;
    public final int TWO_PROBABILITY = 45;
    public final int GRID_SIZE;


    private final Random random;
    private int[][] grid;
    private int score;

    // TODO PA3
    // Constructs a fresh board with random tiles
    public Board(int boardSize, Random random)
    {   
        this.random = random; 
        GRID_SIZE = boardSize; 
        this.grid = new int[boardSize][boardSize];
        score = 0;
        for(int index = 0; index < NUM_START_TILES; index ++){
            this.addRandomTile();
        }
    }




    // TODO PA3
    // Construct a board based off of an input file 
    public Board(String inputBoard, Random random) throws IOException
    {
        this.random = random; 
        Scanner sc = new Scanner (new File (inputBoard));
        GRID_SIZE = sc.nextInt();
        score = sc.nextInt();
        grid = new int[GRID_SIZE][GRID_SIZE];
        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++ ){
                grid[row][col] = sc.nextInt();
            }
        }
    }

    // TODO PA3
    // Saves the current board to a file
    public void saveBoard(String outputBoard) throws IOException
    {
        PrintWriter printer = new PrintWriter (new File(outputBoard));
        printer.println(GRID_SIZE);
        printer.println(score);
        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++ ){
                printer.print(grid[row][col]+" ");
            }
            printer.print("\n");
        }
        printer.close();
    }

    // TODO PA3
    // Adds a Random Tile (of value 2 or 4) to a 
    // Random Empty space on the board
    public void addRandomTile()
    {
        int count = 0;
        int location = 0 , value = 0, position = 0;

        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++ ){
                if(grid[row][col] == 0){
                    count += 1;
                }
            }
        }
        if(count == 0)
            return;
        
        else{
            location = random.nextInt(count);
            value = random.nextInt(100);

            for(int row = 0; row < GRID_SIZE; row++){
                for(int col = 0; col < GRID_SIZE; col++){
                    if (grid[row][col] == 0){
                        if(position == location){
                            if(value < TWO_PROBABILITY)
                                grid[row][col] = 2;
                            
                            else
                                grid[row][col] = 4;
                        }
                        position++;
                    }
                }
            }
        }
    }

    // TODO PA4
    // Check to see if we have a game over
    public boolean isGameOver()
    {
        if(!(canMoveLeft() && canMoveRight() 
        && canMoveUp() && canMoveDown())){
            return true;
        }
        return false;
    }

    // TODO PA4
    // Determine if we can move in a given direction
    public boolean canMoveLeft(){
        boolean result = false;

        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 1; col < GRID_SIZE; col++){
                if(grid[row][col-1] == 0 
                        || grid[row][col] == grid[row][col - 1]){
                    result = true;
                }
            }
        }        

        return result;
    }
    
    public boolean canMoveRight(){
        boolean result = false;

        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE-1; col++){
                if(grid[row][col + 1] == 0 
                        || grid[row][col] == grid[row][col + 1]){
                    result = true;
                }
            }
        }        
        return result;
    }

    public boolean canMoveUp(){
        boolean result = false;

        for(int col = 0; col < GRID_SIZE; col++){
            for(int row = 1; row < GRID_SIZE; row++){
                if(grid[row - 1][col] == 0 
                        || grid[row][col] == grid[row - 1][col]){
                    result = true;
                }
            }
        }        
        return result;
    }

    public boolean canMoveDown(){
        boolean result = false;

        for(int col = 0; col < GRID_SIZE; col++){
            for(int row = 0; row < GRID_SIZE-1; row++){
                if(grid[row + 1][col] == 0 
                        || grid[row][col] == grid[row + 1][col]){
                    result = true;
                }
            }
        }        
        return result;
    }

    public boolean canMove(Direction direction)
    {
        if(canMoveLeft() && move(Direction.LEFT)){
            return true;
        }
        else if(canMoveRight() && move(Direction.RIGHT)){
            return true;
        }
        else if(canMoveUp() && move(Direction.UP)){
            return true;
        }
        else if(canMoveDown() && move(Direction.DOWN)){
            return true;
        }
        else{
            return false;
        }
    }

    // TODO PA4
    // Perform a move Operation
    public boolean move(Direction direction)
    {
        return true;
    }

    // Return the reference to the 2048 Grid
    public int[][] getGrid()
    {
        return grid;
    }

    // Return the score
    public int getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        
        for (int row = 0; row < GRID_SIZE; row++)
        {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -" :
                                    String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }
}
