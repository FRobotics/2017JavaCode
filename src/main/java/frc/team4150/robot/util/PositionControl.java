package main.java.frc.team4150.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionControl {

	private final Distance target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	private final double deadband;
	
	/**
	 * @param target - the distance you want to travel
	 * @param minSpeed - the maximum speed the motor is allowed to travel
	 * @param maxSpeed - the minimum speed the motor is allowed to travel
	 * @param rate - the rate at which the speed should increase
	 */
	public PositionControl(Distance target, double minSpeed, double maxSpeed, double rate, double deadband) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
		this.deadband = deadband;
	}
	
	/**
	 * @param target - the distance you want to travel
	 * @param minSpeed - the maximum speed the motor is allowed to travel
	 * @param maxSpeed - the minimum speed the motor is allowed to travel
	 * @param rate - the rate at which the speed should increase
	 */
	public PositionControl(Distance target, String key) {
		this.target = target;
		this.minSpeed = SmartDashboard.getNumber(key + "PosControl/minSpeed", 0);
		this.maxSpeed = SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0);
		this.rate = SmartDashboard.getNumber(key + "PosControl/rate", 0);
		this.deadband = SmartDashboard.getNumber(key + "PosControl/deadband", 0);
	}
	
	public double getSpeed(Distance distanceTraveled, Distance radius) {
		double target = this.target.toDegrees(radius);
		double traveled = distanceTraveled.toDegrees(radius);
		return Math.min(
				(-this.rate * Math.abs(
						traveled - (target / 2) + (this.deadband / 2)
				) - (this.deadband * this.rate / 2)) +
				this.rate * Math.abs(this.target.toBaseUnit() / 2) + this.minSpeed,
		this.maxSpeed);
	}
	
	public Distance getTarget() {
		return target;
	}
	
	public double getMinSpeed() {
		return minSpeed;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public double getRate() {
		return rate;
	}
	public double getDeadband() {
		return deadband;
	}
}
