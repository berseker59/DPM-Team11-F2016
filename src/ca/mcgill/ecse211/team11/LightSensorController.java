package ca.mcgill.ecse211.team11;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * Operates the light sensor that will be pointed downwards in order to detect lines for odometry
 * correction purposes.
 * 
 * @author Maxence Frenette
 * @version 4.1
 * @since 1.0
 */
public class LightSensorController {
  private SampleProvider sp;
  private float[] data;
  private float lastLightLevel;
  private long lastLightLevelTime;

  public LightSensorController(EV3ColorSensor colorSensor) {
    sp = colorSensor.getRedMode();
    sp = new NonBufferedMedianFilter(sp, Constants.LIGHT_SENSOR_MEDIAN_FILTER_SIZE);
    data = new float[sp.sampleSize()];
  }

  /**
   * Fetches the light level seen by the light sensor.
   * 
   * @return The light level read by the light sensor.
   */
  public synchronized float getLightLevel() {
    sp.fetchSample(data, 0);
    return data[0];
  }

  /**
   * Tests if the light sensor is seeing a line.
   * 
   * @return True is the light sensor is seeing a line, false otherwise.
   */
  public boolean isLineCrossed() {
    float lightLevel = getLightLevel();
    long lightLevelTime = System.currentTimeMillis();
    boolean isLineCrossed;

    if (lightLevelTime - lastLightLevelTime < 250) {
      isLineCrossed = (lightLevel - lastLightLevel) < Constants.LINE_CROSSED_LIGHT_THRESHOLD;
    } else {
      isLineCrossed = false;
    }

    lastLightLevel = lightLevel;
    lastLightLevelTime = lightLevelTime;
    return isLineCrossed;
  }
}
