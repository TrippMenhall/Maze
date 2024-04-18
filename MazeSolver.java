/*
 * Dalton Menhall
 * CSC111_24SP
 * MazeSolver.java
 * Reads a file of a maze and then solves the maze and prints out completed maze with path
 */

// Can prob remove the finish from every method except goNorth bc all finsihes will be north

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver {
    
    // Instance Variables
    private static final char WALL = 'x';
    private static final char CLEAR = ' ';
    private static final char START = 'S';
    private static final char FINISH = 'F';
    private static final char PATH = 'P';
    private static final char VISITED = 'V';

    private static int width;
    private static int height;

    private static int hortizontalLocation;
    private static int verticalLocation;
    // maze[verticalLocation][hortizontalLocation]

    // Moves north and returns true if its the finish, otherwise it calls other methods or backtracks
    public static boolean goNorth(char[][] maze){
        boolean success;
        // if (the square to the north is clear, inside the maze, and unvisited)
        if(verticalLocation-1 < height && verticalLocation-1 > -1 && 
        maze[verticalLocation-1][hortizontalLocation] == CLEAR || 
        maze[verticalLocation-1][hortizontalLocation] == FINISH){
            // Mark the square as part of the path
            maze[verticalLocation][hortizontalLocation] = PATH;
            // Move to the north
            verticalLocation--;
            // if (at exit)
            if(maze[verticalLocation][hortizontalLocation] == FINISH){
                // Maze was successfully compleated
                success = true;
            }else{
                success = goNorth(maze);
                if (!success) {
                    success = goWest(maze);
                    if (!success) {
                        success = goEast(maze);
                        if (!success) {
                            //Mark square visited
                            maze[verticalLocation][hortizontalLocation] = VISITED;
                            //Backtrack south
                            verticalLocation++;
                        } // end if
                    } // end if
                } // end if
            } // end if 
        }else {
            success = false;
        } // end if return success    
        return success;
    } // end goNorth

    // Moves west and returns true if its the finish, otherwise it calls other methods or backtracks
    public static boolean goWest(char[][] maze){
        boolean success;
        // if (the square to the west is clear, inside the maze, and unvisited)
        if(hortizontalLocation-1 < width && hortizontalLocation-1 > -1 &&
        maze[verticalLocation][hortizontalLocation-1] == CLEAR ||
        maze[verticalLocation][hortizontalLocation-1] == FINISH){
            // Mark the square as part of the path
            maze[verticalLocation][hortizontalLocation] = PATH;
            // Move to the west
            hortizontalLocation--;
            // if (at exit)
            if(maze[verticalLocation][hortizontalLocation] == FINISH){
                // Maze was successfully compleated
                success = true;
            }else{
                success = goWest(maze);
                if (!success) {
                    success = goSouth(maze);
                    if (!success) {
                        success = goNorth(maze);
                        if (!success) {
                            //Mark square visited
                            maze[verticalLocation][hortizontalLocation] = VISITED;
                            //Backtrack east
                            hortizontalLocation++;
                        } // end if
                    } // end if
                } // end if
            } // end if 
        }else {
            success = false;
        } // end if return success    
        return success;
    } // end goWest

    // Moves south and returns true if its the finish, otherwise it calls other methods or backtracks
    public static boolean goSouth(char[][] maze){
        boolean success;
        // if (the square to the south is clear, inside the maze, and unvisited)
        if(verticalLocation+1 < height && verticalLocation+1 > -1 &&
        maze[verticalLocation+1][hortizontalLocation] == CLEAR ||
        maze[verticalLocation+1][hortizontalLocation] == FINISH){
            // Mark the square as part of the path
            maze[verticalLocation][hortizontalLocation] = PATH;
            // Move to the south
            verticalLocation++;
            // if (at exit)
            if(maze[verticalLocation][hortizontalLocation] == FINISH){
                // Maze was successfully compleated
                success = true;
            }else{
                success = goSouth(maze);
                if (!success) {
                    success = goEast(maze);
                    if (!success) {
                        success = goWest(maze);
                        if (!success) {
                            //Mark square visited
                            maze[verticalLocation][hortizontalLocation] = VISITED;
                            //Backtrack north
                            verticalLocation--;
                        } // end if
                    } // end if
                } // end if
            } // end if 
        }else {
            success = false;
        } // end if return success    
        return success;
    } // end goSouth

    // Moves east and returns true if its the finish, otherwise it calls other methods or backtracks
    public static boolean goEast(char[][] maze){
        boolean success;
        // if (the square to the east is clear, inside the maze, and unvisited)
        if(hortizontalLocation+1 < width && hortizontalLocation+1 > -1 &&
        maze[verticalLocation][hortizontalLocation+1] == CLEAR || 
        maze[verticalLocation][hortizontalLocation+1] == FINISH){
            // Mark the square as part of the path
            maze[verticalLocation][hortizontalLocation] = PATH;
            // Move to the east
            hortizontalLocation++;
            // if (at exit)
            if(maze[verticalLocation][hortizontalLocation] == FINISH){
                // Maze was successfully compleated
                success = true;
            }else{
                success = goEast(maze);
                if (!success) {
                    success = goNorth(maze);
                    if (!success) {
                        success = goSouth(maze);
                        if (!success) {
                            //Mark square visited
                            maze[verticalLocation][hortizontalLocation] = VISITED;
                            //Backtrack west
                            hortizontalLocation--;
                        } // end if
                    } // end if
                } // end if
            } // end if 
        }else {
            success = false;
        } // end if return success    
        return success;
    } // end goEast

    // Method that prints out the maze
    public static void printMaze(char[][] maze){
        for(int i = 0; i < height; i++){
            for(int z = 0; z < width; z++){
                // prints out each part in the maze
                System.out.print(maze[i][z]);
            }
            // goes to next line
            System.out.println();
        }
    }

    public static void main(String[] args){
        
        Scanner fileInput;

        // inFile is set to the file of choice
        File inFile = new File("maze.txt");

        try{
            fileInput = new Scanner(inFile);

            // width and height set to the first 2 ints
            width = fileInput.nextInt();
            height = fileInput.nextInt();

            // endWidth and endHeight set to the next 2 ints
            int endWidth = fileInput.nextInt();
            int endHeight = fileInput.nextInt();
            
            // startWidth and startHeight set to the next 2 ints
            int startWidth = fileInput.nextInt();
            int startHeight = fileInput.nextInt();

            // clear out the extra line
            fileInput.nextLine();

            // maze 2d array created with width and height
            char[][] maze = new char[height][width];

            // Reads in maze and adds start and finish
            for(int i = 0; i < height; i++){
                // Reads the line in from maze.txt
                String inputLine = fileInput.nextLine();

                // assigns the original maze into the new maze and adds the start and finish
                for(int z = 0; z < inputLine.length(); z++){
                    if(i == endHeight && z == endWidth){
                        // Adds in the finish
                        maze[i][z] = FINISH;
                    }else if(i == startHeight && z == startWidth){
                        // Adds in the start
                        maze[i][z] = START;
                        // Adds the current location as start
                        verticalLocation = i;
                        hortizontalLocation = z;
                    }else{
                        // Reads in the maze into the 2d-array
                        maze[i][z] = inputLine.charAt(z);
                    }
                }
            }

            // Prints out maze
            printMaze(maze);

            // A little space between the 2 mazes
            System.out.println();

            // Completes the maze and prints out if failed or success
            if(goNorth(maze)){
                System.out.println("Maze was completed!!");
                // Prints out the completed maze
                printMaze(maze);
            }else{
                System.out.println("Maze has no solution");
            }

            // Close scanner
            fileInput.close();

        }catch (FileNotFoundException e){
            // if no file found then it prints the error and exits program
            System.out.println(e);
            System.exit(1); // IO error; exit program
        } // end catch
    }
}
