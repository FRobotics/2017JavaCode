package main.java.frc.team4150.robot.util;

public class Util {
	
	public static final double inchPerDegree = Math.PI * 2 / 360;

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
    
    public static double toDegrees(double inches, double radius) {
		return inches / inchPerDegree / radius;
	}
	
	public static double fromDegrees(double degrees, double radius) {
		return degrees * inchPerDegree * radius;
	}

}
