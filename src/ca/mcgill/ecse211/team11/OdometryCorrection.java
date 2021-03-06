package ca.mcgill.ecse211.team11;

/**
 * Corrects the odometer's position by using the lines on the ground.
 * 
 * @author Maxence Frenette
 * @version 4.1
 * @since 1.0
 */
public class OdometryCorrection extends Thread {
  private LightSensorController lsc;
  private Odometer odometer;

  public OdometryCorrection(Initializer init) {
    lsc = init.lightSensorController;
    odometer = init.odometer;
  }

  @Override
  /**
   * Entry point of the OdometryCorrection thread
   */
  public void run() {
    while (true) {
      if (lsc.isLineCrossed()) {
        double x = odometer.getX();
        double y = odometer.getY();
        double theta = odometer.getTheta();

        double lightSensorX =
            x + Constants.DIST_CENTER_TO_LINE_DETECTION_LIGHT_SENSOR * Math.cos(theta);
        double lightSensorY =
            y + Constants.DIST_CENTER_TO_LINE_DETECTION_LIGHT_SENSOR * Math.sin(theta);

        double dx = Util.specialMod(lightSensorX, Constants.GRID_SIZE);
        double dy = Util.specialMod(lightSensorY, Constants.GRID_SIZE);

        if (Math.abs(dx) < Constants.ODOMETRY_CORRECTION_MAX_ERROR_MARGIN
            && Math.abs(dy) < Constants.ODOMETRY_CORRECTION_MAX_ERROR_MARGIN) {
          // No correction can be made since the light sensor just hit a corner
        } else if (Math.abs(dx) < Constants.ODOMETRY_CORRECTION_MAX_ERROR_MARGIN) {
          odometer.setX(x + dx);
        } else if (Math.abs(dy) < Constants.ODOMETRY_CORRECTION_MAX_ERROR_MARGIN) {
          odometer.setY(y + dy);
        } else {
          // The position error has grown over the maximum error margin. This is not good.
          // TODO Trigger a relocalization.
          Logger.logData("The robot's position error has grown over "
              + Constants.ODOMETRY_CORRECTION_MAX_ERROR_MARGIN
              + "cm. No odometry correction could be done.");
        }
      }

      Util.sleep(Constants.ODOMETRY_CORRECTION_WAIT_PERIOD);
    }
  }
}
