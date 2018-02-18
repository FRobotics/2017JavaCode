package main.java.frc.team4150.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.util.Distance.Unit;

public class PositionControl {

	private final Distance target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	private final double deadband;

	private final Distance wheelRadius;

	/**
	 * @param target
	 *            - the distance you want to travel
	 * @param minSpeed
	 *            - the maximum speed the motor is allowed to travel
	 * @param maxSpeed
	 *            - the minimum speed the motor is allowed to travel
	 * @param rate
	 *            - the rate at which the speed should increase
	 */
	public PositionControl(Distance target, double minSpeed, double maxSpeed, double rate, Distance deadband,
			Distance wheelRadius) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
		this.deadband = deadband.toBaseUnit();

		this.wheelRadius = wheelRadius;
	}

	/**
	 * @param target
	 *            - the distance you want to travel
	 * @param minSpeed
	 *            - the maximum speed the motor is allowed to travel
	 * @param maxSpeed
	 *            - the minimum speed the motor is allowed to travel
	 * @param rate
	 *            - the rate at which the speed should increase
	 */
	public PositionControl(Distance target, String key, Distance wheelRadius) {
		this(	target, SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.5),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				new Distance(SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), Unit.FEET), wheelRadius);
	}

	public boolean onTarget(Distance distanceTraveled) {
		double target = this.target.toBaseUnit();
		double traveled = distanceTraveled.toBaseUnit();
		if (Math.abs(target - traveled) > deadband) {
			return false;
		} else {
			return true;
		}
	}

	public double getSpeed(Distance distanceTraveled) {
		double target = this.target.toDegrees(wheelRadius);
		double traveled = distanceTraveled.toDegrees(wheelRadius);
		return Math.min((-this.rate * Math.abs(traveled - (target / 2) + (this.deadband / 2))
				- (this.deadband * this.rate / 2)) + this.rate * Math.abs(this.target.toBaseUnit() / 2) + this.minSpeed,
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
