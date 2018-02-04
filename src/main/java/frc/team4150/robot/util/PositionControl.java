package main.java.frc.team4150.robot.util;

public class PositionControl {

	private final Distance target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	
	/**
	 * 
	 * @param target - the distance you want to travel
	 * @param minSpeed - the maximum speed the motor is allowed to travel
	 * @param maxSpeed - the minimum speed the motor is allowed to travel
	 * @param rate - the rate at which the speed should increase
	 */
	public PositionControl(Distance target, double minSpeed, double maxSpeed, double rate) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
	}
	
	public double getSpeed(Distance distanceTraveled) {
		return Math.max(Math.min(
				-this.rate * Math.abs(distanceTraveled.toBaseUnit() - (this.target.toBaseUnit() / 2)) +
				this.rate * Math.abs(this.target.toBaseUnit() / 2) +
				this.minSpeed,
		this.maxSpeed), this.minSpeed);
	}

}
