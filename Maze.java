/****
 * A class to store and traverse a maze
 * @author esahe2 - Coded the class, less the traverse method to solve the maze.
 * @author Jason Schenck - Coded the traverse method algorithm to efficiently find the correct solution path.
 *
 */
import java.util.*;
public class Maze {
 
	/**
	 * Two dimensional array to represent a maze
	 */
	private char[][] maze;
	
	/**
	 * Constructor initializing the maze array
	 * @param maze
	 */
	public Maze(char[][] maze)
	{
		this.maze= maze;
	}
	
/**      *
         * @author Jason Schenck
	 * @method This method will take and solve a maze in efficient time   
	 * @param start: The start Position in the maze
	 * @param goal: The goal Position in the maze
	 * @return An array of Position which stores a solution to the maze. If a solution is not found a null value should be returned.
	 * 
         */
	public Position[] traverse(Position start, Position goal)
	{
            // Validate parameters passed as start and goal
            // Both must be valid array positions 
            // Both must contain a blank space
            // Array also must not be null or empty
            
            boolean emptyArray = true;
            for (int row = 0; row < maze.length; row++){
                for (int column = 0; column < maze[row].length; column++){
                    if (maze[row][column] != ' ' && maze[row][column] != 'x'){
                        // Invalid data found in maze
                        // break and return null to user
                        System.out.println("The maze contains invalid characters!");
                        return null;
                    }
                    if (maze[row][column] != ' '){
                        // If maze array is valid, test if it is empty
                        // Test if every space is blank, if so then empty array
                        // If any space of the valid array is not empty, emptyArray is false.
                        emptyArray = false;
                    }
                }
            }
            // if empty flag is true, return null and notify user
            if (emptyArray){
                System.out.println("The maze array is valid, but it is EMPTY!");
                return null;
            }
            
            // validate the passed in positions as valid before traversing
            int startRowNum = start.getRow();
            int startColNum = start.getColumn();
            int goalRowNum = goal.getRow();
            int goalColNum = goal.getColumn();
            
            // valid start and goal positions
            // must be within the bounds of the 2D array and also must be equal to blank space
            if(startRowNum > maze.length - 1 || startColNum > maze[0].length - 1 ||
                    maze[startRowNum][startColNum] != ' '){
                // Then this start position fails validity
                System.out.println("The start position failed validity check!");

                return null;
            }
            if(goalRowNum > maze.length || goalColNum > maze[0].length ||
                    maze[goalRowNum][goalColNum] != ' '){
                // Then this goal position fails validity
                System.out.println("The goal position failed validity check!");
                return null;
            }
            
            // Once reached, data in array is a valid maze and start,goal positions are valid
            // Begin traversing using stack
            
            // Create an empty stack to hold our tracing for paths
            Stack<Position> pathTrace = new Stack<Position>();
            
            // Push the starting position on the stack
            pathTrace.push(start);
                      
            
            // To keep track of which position we are at, use a current variable
            Position current = start;
            
            // mark starting position as visited
            maze[current.getRow()][current.getColumn()] = 'v';              
                       
            
           
            while (!current.equals(goal) && !pathTrace.isEmpty()){
                // check left neighbor if empty, push on to stack
                //TESTING
                System.out.println("ROW : " + current.getRow());
                System.out.println("COLUMN : " + current.getColumn());
                
                // inititalize and reset moved booleans to prevent index out of bounds errors
                boolean movedLeft = false;
                boolean movedRight = false;
                boolean movedUp = false;
                boolean movedDown = false;
                
                // begin by checking left neighbor
                // if in bounds, and empty move there
                if (current.getColumn() - 1 >= 0){
                if(maze[current.getRow()][current.getColumn() - 1] == ' ' &&
                        !current.equals(goal)) {
                    
                    pathTrace.push(new Position(current.getRow(),current.getColumn() - 1));
                    // TESTING
                    System.out.println("Left neighbor is empty at: " + pathTrace.peek().toString());
                    System.out.println("MOVE LEFT");
                    
                    // mark left position as visited
                    maze[current.getRow()][current.getColumn() - 1] = 'v';
                    
                    // update current position
                    current = pathTrace.peek();                    
                    
                    movedLeft = true;
                    
                }
                }
                
                // if left neighbor is not empty, check right neighbor and if empty push on to stack
                if (current.getColumn() +1 <= maze[0].length - 1){
                if(!movedLeft && maze[current.getRow()][current.getColumn() + 1] == ' ' &&
                        !current.equals(goal)){
                    
                    pathTrace.push(new Position(current.getRow(),current.getColumn() + 1));
                    // TESTING
                    System.out.println("Right neighbor is empty at: " + pathTrace.peek().toString());
                    System.out.println("MOVE RIGHT");
                    
                    // mark right position as visited
                    maze[current.getRow()][current.getColumn() + 1] = 'v';  
                    
                    // update current position
                    current = pathTrace.peek();
                    
                    movedRight = true;
                }
                }
                // If left and right are not empty, check up neighbor if empty push onto stack
                if (current.getRow() - 1 >= 0){
                if(!movedLeft && !movedRight && maze[current.getRow() - 1][current.getColumn()] == ' ' &&
                        !current.equals(goal)){
                   
                    pathTrace.push(new Position(current.getRow() - 1,current.getColumn()));
                    // TESTING
                    System.out.println("Up neighbor is empty at: " + pathTrace.peek().toString());
                    System.out.println("MOVE UP");       
                    
                    // mark up position as visited
                    maze[current.getRow() - 1][current.getColumn()] = 'v'; 
           
                    // update current position
                    current = pathTrace.peek(); 
                    
                    movedUp = true;
                }
                }
                // If left, right, and up neighbors not empty, check down neighbor
                if (current.getRow() + 1 <= maze.length - 1){
                if(!movedLeft && !movedRight && !movedUp && maze[current.getRow() + 1][current.getColumn()] == ' '
                        && !current.equals(goal)){
                    
                    pathTrace.push(new Position(current.getRow() + 1,current.getColumn()));
                    // TESTING
                    System.out.println("Down neighbor is empty at: " + pathTrace.peek().toString());
                    System.out.println("MOVE DOWN");     
                    
                    // mark down position as visited
                    maze[current.getRow() + 1][current.getColumn()] = 'v';
                   
                    // update current position
                    current = pathTrace.peek();
                    movedDown = true;
                }        
                }
                // if no neighbors are empty, then dead end reached pop from stack to back track and reloop
                if(!movedLeft && !movedRight && !movedUp && !movedDown && !current.equals(goal)){
                    
                    // mark dead end position as visited
                    maze[current.getRow()][current.getColumn()] = 'v'; 
                    
                    //TESTING
                    System.out.println("POP OCCURRED");
                    // pop most recent position off the stack to back track
                    pathTrace.pop();
                    
                    //update current
                    current = pathTrace.peek();
                }                
                
              
                
                // Continue looping until either stack is empty of goal is reached             
                
            }
                
            System.out.println(pathTrace.size());
            
           Position[] solutionArray = new Position[pathTrace.size()];
           
           if (!pathTrace.isEmpty()){
               for(int i = pathTrace.size() - 1; i >= 0; i--){
                   solutionArray[i] = pathTrace.pop();
               }
               return solutionArray; 
           }
           else{
               return null;
           } 
        } // End traverse()
    } // End Class
	
	
	
	