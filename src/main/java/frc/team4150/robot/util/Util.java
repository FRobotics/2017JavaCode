package main.java.frc.team4150.robot.util;

public class Util {

    /**
     * Limit an integer to the -1.0 to +1.0 range
     */
    public static double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }

}
