package main.java.frc.team4150.robot.util;

public class Time {

    public enum Unit {
        MILLIS(1),
        SEC(1000),
        MIN(1000 * 60);
        private int millisMultiplier;

        Unit(int millisMultiplier) {
            this.millisMultiplier = millisMultiplier;
        }

        public int getMultiplier() {
            return millisMultiplier;
        }
    }

    private long millis;

    /**
     * Creates a new time in the specified unit
     * @param time
     * @param unit
     */
    public Time(long time, Unit unit) {
        this.millis = time * unit.getMultiplier();
    }

    /**
     * Converts the time to the specified unit
     * @param unit
     * @return the time in the specified unit
     */
    public double to(Unit unit) {
        return millis/unit.getMultiplier();
    }

}
