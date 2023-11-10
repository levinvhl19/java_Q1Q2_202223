package _test.backtracking;

public class MazeSolver {
	private Maze maze;
	private int count;
	
	public MazeSolver() {
		maze = new Maze(MazeWalls.field_20x20);
	    maze.playerX = 1; maze.playerY = 1;
	    maze.goalX = 15; maze.goalY = 16;
		maze.waitingTimeMilliSeconds = 5;
		count = 0;
		

	}

	private boolean findGoal() {
		
		if(maze.isPlayerOnGoal()) {
			return maze.isPlayerOnGoal();
		}
		
//		if(maze.isPlayerOnVisited()) {
//			return false;
//		}
		
		for(int i = 0; i < 4; i++) {
			
			if(!maze.isWallInDirection(i)) {
				maze.move(i);
				if(!maze.isPlayerOnVisited()) {
					count++;
					maze.markPlayerFieldAsVisited(true);
					boolean suc=findGoal();
					if(suc) {return true;}
					maze.markPlayerFieldAsVisited(false);
				}
				
				
				maze.moveBack(i);
				count--;
				
			}
		}
		
		
		return false;
	}
	
	
	public static void main(String[] args) {
		MazeSolver m = new MazeSolver();
		boolean success = m.findGoal();
		if(success) {
			System.out.println("Am Ziel angekommen :)");
		}
		else {
			System.out.println("Nicht angekommen :(");
		}
	}

}
