package main.java.frc.team4150.robot.util;

public class Distance {
	
	public static final double inchPerDegree = Math.PI * 2 / 360;

	public enum Unit {
		INCHES(1), FEET(12);

		private int inchesMultiplier;

		Unit(int degreesMultiplier) {
			this.inchesMultiplier = degreesMultiplier;
		}

		public int getMultiplier() {
			return inchesMultiplier;
		}

		public double getDegrees(double radius) {
			return this.getMultiplier() * Math.PI * 2 / 360 * radius;
		}
	}

	private double inches;

	public Distance(double distance, Unit unit) {
		this.inches = distance * unit.getMultiplier();
	}
	
	public Distance(double distance) {
		this(distance, Unit.INCHES);
	}

	public double to(Unit unit) {
		return this.inches / unit.getMultiplier();
	}
	
	public double toBaseUnit() {
		return this.inches;
	}
	
	public double toDegrees(Distance radius) {
		return this.inches / inchPerDegree / radius.to(Unit.INCHES);
	}

}
