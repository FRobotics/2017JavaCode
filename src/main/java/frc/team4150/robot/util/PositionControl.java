package main.java.frc.team4150.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionControl {

	private final double target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	private final double deadband;
	private final boolean startMax;

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
	public PositionControl(double target, double minSpeed, double maxSpeed, double rate, double deadband, boolean startMax) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
		this.deadband = deadband;
		this.startMax = startMax;
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
	public PositionControl(double target, String key, boolean startMax) {
		this(	target, SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.8),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), startMax);
	}

	public PositionControl(String key, boolean startMax) {
		this(	SmartDashboard.getNumber(key + "PosControl/target", 0),
				SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.3),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), startMax);
	}

	public boolean onTarget(double distanceTraveled) {
		double traveled = Math.abs(distanceTraveled);
		double target = Math.abs(this.target);
		if (target - traveled < deadband) {
			return true;
		} else {
			return false;
		}
	}

	public double getSpeed(double distanceTraveled) {
		double traveled = Math.abs(distanceTraveled);
		double target = Math.abs(this.target);
		double speed = 0;
		if (traveled < (target - deadband) - (maxSpeed - minSpeed) / rate) {
			if(startMax) speed = maxSpeed;
			else speed = Math.min(rate * traveled + minSpeed, maxSpeed);
		} else if (traveled < target - traveled) {
			speed = -rate * (traveled - (target - deadband));
		} else {
			speed = 0;
		}
		SmartDashboard.putString("testing", traveled + "/" + target + ": " + speed);
		return this.target > 0 ? speed : -speed;
	}

	public double getTarget() {
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
