//------------------------------------------------------------------//
// PA3Tester.java                                                   //
//                                                                  //
// Simple Tester for students to use while doing PA3 (2048 Part 1)  //
//                                                                  //
// Author:  Brandon Williams                                        //
// Date:    4/9/15                                                  //
//------------------------------------------------------------------//

/** DO NOT MODIFY */

import java.util.*;
import java.io.*;

public class PA3Tester
{
    private static int SEED = 2015;
    private static String inputBoard = "pa3Test.board";
    private static String outputBoard = "student.board";
    private static int inputScore = 2481632;
    private static int[][] inputGrid = {{2,128,256,32768},
                                        {2,64,512,16384},
                                        {4,32,1024,8192},
                                        {8,16,2048,4096}};

    // All these test boards are based off the SEED 2015
    private static int[][][] testGrid = {{{0,0,0,0},
                                          {0,0,0,0},    // Grid 0
                                          {0,2,0,0},
                                          {0,0,2,0}},

                                         {{0,0,0,0},
                                          {0,2,0,0},    // Grid 1
                                          {0,2,0,0},
                                          {0,0,2,0}},
                                         
                                         {{0,0,0,4},
                                          {0,2,0,0},    // Grid 2
                                          {0,2,0,0},
                                          {0,0,2,0}},
                                         
                                         {{0,0,0,4},
                                          {0,2,0,0},    // Grid 3
                                          {0,2,2,0},
                                          {0,0,2,0}},

                                         {{0,0,0,4},
                                          {0,2,0,0},    // Grid 4
                                          {0,2,2,0},
                                          {0,0,2,2}},

                                         {{0,0,0,4},
                                          {2,2,0,0},    // Grid 5
                                          {0,2,2,0},
                                          {0,0,2,2}},

                                         {{0,0,0,4},
                                          {2,2,0,2},    // Grid 6
                                          {0,2,2,0},
                                          {0,0,2,2}}};

    public static void main(String[] args) throws IOException,
                                                  InterruptedException
    {
        System.out.println("Begining Testing PA3");

        testBoardCtor1();
        testBoardCtor2();
        testAddRandomTile();
        testSaveBoard();
    }

    private static void testBoardCtor1() throws IOException
    {
        Board studentBoard;

        // Test the Constructor which generates a new board
        System.out.print("Testing Board(int, random) Constructor......");

        try
        {
            studentBoard = new Board(4, new Random(SEED));

            int[][] studentGrid = studentBoard.getGrid();
            if(studentGrid == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            if(studentBoard.GRID_SIZE != 4)
            {
                System.out.println("FAILED!");
                System.out.println("You didn't set the GRIDSIZE correctly");
                System.out.println("Should be: " + 4 + 
                                   " Yours is: " + studentBoard.GRID_SIZE);
                return;
            }

            if(studentBoard.getScore() != 0)
            {
                System.out.println("FAILED!");
                System.out.println("Score is incorrect");
                System.out.println("Should be: " + 0 + 
                                   " Yours is: " + studentBoard.getScore());
                return;
            }

            if(!Arrays.deepEquals(testGrid[0], studentBoard.getGrid()))
            {
                System.out.println("FAILED!");
                System.out.println("Your 2D grid isn't correct");
                System.out.println("This may be due to your " + 
                                   "addRandomTile Method");
                System.out.println("Should be: ");
                print2DArray(testGrid[0]);
                System.out.println("Yours is: ");
                print2DArray(studentBoard.getGrid());
                return;
            }

            System.out.println("Passed!");
        }
        catch(Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying " +
                               "to create a new Board");
            e.printStackTrace();
            return;
        }
    }

    private static void testBoardCtor2() throws IOException
    {
        Board studentBoard;
        // Test the Constructor which Loads a saved board
        System.out.print("Testing Board(string, random) Constructor...");

        try
        {
            studentBoard = new Board(inputBoard, new Random(SEED));

            int[][] studentGrid = studentBoard.getGrid();
            if(studentGrid == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            if(studentBoard.GRID_SIZE != 4)
            {
                System.out.println("FAILED!");
                System.out.println("You didn't set the GRIDSIZE correctly");
                System.out.println("Should be: " + 4 + 
                                   " Yours is: " + studentBoard.GRID_SIZE);
                return;
            }

            if(studentBoard.getScore() != inputScore)
            {
                System.out.println("FAILED!");
                System.out.println("Score is incorrect");
                System.out.println("Should be: " + inputScore + 
                                   " Yours is: " + studentBoard.getScore());
                return;
            }

            if(!Arrays.deepEquals(inputGrid, studentBoard.getGrid()))
            {
                System.out.println("FAILED!");
                System.out.println("Your 2D grid isn't correct");
                System.out.println("You didn't load the board properly");
                System.out.println("Should be: ");
                print2DArray(inputGrid);
                System.out.println("Yours is: ");
                print2DArray(studentBoard.getGrid());
                return;
            }

            System.out.println("Passed!");
        }
        catch(Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying " +
                               "to load a board from a file");
            e.printStackTrace();
            return;
        }
        catch(OutOfMemoryError e)
        {
            System.out.println("FAILED!");
            System.out.println("Check how you are parsing the input " +
                               "file.  You are probably using the score\n" +
                               "as the GRID_SIZE which causes an exception " +
                               "when trying to create the grid array.");
            e.printStackTrace();
            return;
        }
    }

    private static void testAddRandomTile()
    {
        System.out.print("Testing addRandomTile Method................");

        try
        {
            Board studentBoard = new Board(4, new Random(SEED));

            if(studentBoard.getGrid() == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            for(int i = 0; i < testGrid.length; i++)
            {
                if(!Arrays.deepEquals(testGrid[i], studentBoard.getGrid()))
                {
                    System.out.println("FAILED!");
                    System.out.println("Your 2D grid isn't correct");
                    System.out.println("Your addRandomTile method didn't " +
                                       "add the proper tile in the correct " +
                                       "location");
                    System.out.println("Should be: ");
                    print2DArray(testGrid[i]);
                    System.out.println("Yours is: ");
                    print2DArray(studentBoard.getGrid());
                    return;
                }
                
                studentBoard.addRandomTile();
            }

            System.out.println("Passed!");
        }
        catch (Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying to " +
                               "run the addRandomTile Method");
            e.printStackTrace();
            return;
        }
    }

    private static void testSaveBoard() throws IOException,
                                               InterruptedException
    {
        System.out.print("Testing saveBoard Method....................");

        try
        {
            Board studentBoard = new Board(inputBoard, new Random(SEED));

            studentBoard.saveBoard(outputBoard);

            String command = "diff " + outputBoard + " " + inputBoard;
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            if(p.exitValue() == 0)
            {
                System.out.println("Passed!");
            }
            else
            {
                System.out.println("FAILED!");
                System.out.println("Your saveBoard method is incorrect");
                System.out.println("Run the following command to see the " +
                                   "differences:\n\t" + command);
                return;
            }
        }
        catch (Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying to " +
                               "save the board to a file");
            e.printStackTrace();
            return;
        }
        catch(OutOfMemoryError e)
        {
            System.out.println("FAILED!");
            System.out.println("Check how you are parsing the input " +
                               "file.  You are probably using the score\n" +
                               "as the GRID_SIZE which causes an exception " +
                               "when trying to create the grid array.");
            e.printStackTrace();
            return;
        }
    }

    private static void print2DArray(int[][] array)
    {
       for(int i = 0; i < array.length; i++)
           System.out.println(Arrays.toString(array[i]));
    }
}
