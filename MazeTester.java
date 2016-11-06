
/**
 * A simple driver class to test the maze traversal
 * @author Ellie
 * Below is what should be printed by your traverse method:
row:1 column:0
row:1 column:1
row:1 column:2
row:2 column:2
row:2 column:3
row:2 column:4
row:1 column:4
row:1 column:5
row:1 column:6
row:2 column:6
row:2 column:7
row:2 column:8
row:2 column:9
row:3 column:9
row:4 column:9
row:4 column:10
row:4 column:11
row:5 column:11
row:5 column:12
The goal is reached
 * 
 */
public class MazeTester {

	public static void main(String[] args)
	{       // Below is the actual maze, 'x' denotes a wall or dead end
		char[][] sample_maze ={
			   {'x','x','x','x','x','x','x','x','x','x','x','x','x'},
			   {' ',' ',' ','x',' ',' ',' ','x','x','x','x',' ','x'},
			   {'x','x',' ',' ',' ','x',' ',' ',' ',' ','x',' ','x'},
			   {'x','x',' ','x','x',' ','x',' ','x',' ','x',' ','x'},
			   {'x','x',' ',' ','x',' ',' ',' ','x',' ',' ',' ','x'},
			   {'x','x','x','x','x','x','x',' ','x',' ',' ',' ',' '},
			   {'x','x','x','x','x','x','x','x','x','x','x','x','x'}
	         };

	Maze m = new Maze(sample_maze);
	Position[] sol= m.traverse(new Position(1,0), new Position(5,12));
	if (sol!= null)
	{
		for (int i=0; i<sol.length; i++)
			System.out.println(sol[i]);
		System.out.println("The goal is reached");
	}
	else
		System.out.println("This maze has no solution");
}
}