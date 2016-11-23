package ca.mcgill.ecse211.team11.pathfinding;

import ca.mcgill.ecse211.team11.Constants;
import ca.mcgill.ecse211.team11.Initializer;
import ca.mcgill.ecse211.team11.Logger;

/**
 * Stores the internal representation of the field.
 * <p>
 * The field is divided in discrete sectors that each have a state.
 * 
 * @author Maxence Frenette
 * @version 3.0
 * @since 2.0
 */
public class InternalGrid {
  private InternalGridSquare[][] grid;

  public InternalGrid(Initializer init) {
    grid = new InternalGridSquare[2 * Constants.BOARD_SIZE][2 * Constants.BOARD_SIZE];
  }

  /**
   * Travels to the specified location while avoiding obstacles
   */
  public void pathfindTo(double x, double y) {
    // TODO
  }

  /**
   * Scans the surrounding with the ultrasonic sensor area by rotating on itself.
   */
  public void scan() {
    // TODO
  }
  
  public void printBoard()
  {
	  String board = "";
	  for (int i = 0; i<grid.length; i++)
	  {
		  for (int j = 0; j<grid.length; j++)
		  {
			  board = board + "|" + i + "|" + " |" + j + "| {" + grid[i][j] + "} ";
		  }
		  board = board + "\n ";
	  }
	  Logger.logData(board);
  }
}
