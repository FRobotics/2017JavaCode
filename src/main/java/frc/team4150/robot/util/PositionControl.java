package main.java.frc.team4150.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionControl {

	private final double target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	private final double deadband;

	private final double wheelRadius;

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
	public PositionControl(double target, double minSpeed, double maxSpeed, double rate, double deadband,
			double wheelRadius) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
		this.deadband = deadband;

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
	public PositionControl(double target, String key, double wheelRadius) {
		this(	target, SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.5),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), wheelRadius);
	}
	
	public PositionControl(String key, double wheelRadius) {
		this(	SmartDashboard.getNumber(key + "PosControl/target", 0),
				SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.3),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), wheelRadius);
	}

	public boolean onTarget(double distanceTraveled) {
		double traveled = distanceTraveled;
		if (Math.abs(target - traveled) < deadband) {
			return true;
		} else {
			return false;
		}
	}

	public double getSpeed(double distanceTraveled) {
		double traveled = distanceTraveled;
		double speed = 0;
		if (traveled < (target - deadband) - (maxSpeed - minSpeed) / rate) {
			speed = Math.min(rate * traveled + minSpeed, maxSpeed);
		} else if (traveled < target - traveled) {
			speed = -rate * (traveled - (target - deadband));
		} else {
			speed = 0;
		}
		SmartDashboard.putString("testing", traveled + "/" + target + ": " + speed);
		return speed;
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
