package ca.mcgill.ecse211.team11;

/**
 * Main class of the system. Initializes ans passes on the control to Brain.
 * 
 * @author Maxence
 * @version 4.0
 * @since 1.0
 */
public class Main {

  /**
   * Entry point of the program.
   * <p>
   * Initializes all the robot's component and starts the main algorithm.
   * 
   * @param args
   */
  public static void main(String[] args) {
    Initializer init = new Initializer();
    RobotBrain brain = new RobotBrain(init);
    brain.start();
  }
}
